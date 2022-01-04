package com.ex02.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ex02.domain.BoardAttachVO;
import com.ex02.domain.BoardVO;
import com.ex02.domain.Criteria;
import com.ex02.domain.PageDTO;
import com.ex02.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService boardService;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list");
//		model.addAttribute("list", boardService.getList());
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list : "+cri);
		model.addAttribute("list", boardService.getList(cri));
		
		int total = boardService.getTotal(cri);
		
		log.info("list total : "+total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		log.info("register view Call!!");
	}
	
	//시큐어티 적용	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes redirectatt) {
		log.info("register :"+board);
		
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		
		log.info("==========================");
		
		boardService.register(board);
		redirectatt.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";		
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get or modify:"+ bno);
		
		model.addAttribute("board", boardService.get(bno));
	}
	
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board,@ModelAttribute("cri") Criteria cri, RedirectAttributes redirectatt) {
		log.info("modify :"+board);
		if(boardService.modify(board)) {
			redirectatt.addFlashAttribute("result", "success");
		}
		
		//Link 파라 미터 줄이기 위해 UriComponentsBuilder 사용
//		redirectatt.addAttribute("pageNum", cri.getPageNum());
//		redirectatt.addAttribute("amount", cri.getAmount());
//		redirectatt.addAttribute("type", cri.getType());
//		redirectatt.addAttribute("keyword", cri.getKeyword());		
//		
//		return "redirect:/board/list";
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno ,@ModelAttribute("cri") Criteria cri, RedirectAttributes redirectatt) {
		log.info("remove :"+bno);
		
		List<BoardAttachVO> attchList = boardService.getAttachList(bno);
		
		if(boardService.remove(bno)) {
			
			deleteFiles(attchList);
			
			redirectatt.addFlashAttribute("result", "success");
		}
		
//		redirectatt.addAttribute("pageNum", cri.getPageNum());
//		redirectatt.addAttribute("amount", cri.getAmount());
//		redirectatt.addAttribute("type", cri.getType());
//		redirectatt.addAttribute("keyword", cri.getKeyword());
//		return "redirect:/board/list";
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		log.info("getAttachList :" +bno);
		return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
	}
	
	//물리적 파일 삭제 처리
	private void deleteFiles(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0)	{return;}
		
		log.info("delete attach files.......");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			Path file = Paths.get("C:\\Temp\\"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+ attach.getFileName());
			try {
				
				Files.deleteIfExists(file);
				//파일이 이미지면 썸메일이미지도 삭제
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\Temp\\"+attach.getUploadPath()+"\\s_" + attach.getUuid()+"_"+ attach.getFileName());
					Files.delete(thumbNail);
				}
				
			} catch (IOException e) {
				log.error("delete file error"+e.getMessage());
			}
		});
	}
}
