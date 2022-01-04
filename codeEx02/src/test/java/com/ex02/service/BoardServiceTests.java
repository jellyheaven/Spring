package com.ex02.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ex02.domain.BoardVO;
import com.ex02.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService boardService;
	
	@Test
	public void testExist() {
		log.info(boardService);
		assertNotNull(boardService);
	}
	
	@Test
	public void testGetList() {
		//boardService.getList().forEach(board -> log.info(board));
		log.info("=======testGetList=========");
		boardService.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로작성하는 글 서비스");
		board.setContent("새로 작성하는 내용 서비스");
		board.setWriter("snewbie");
		
		boardService.register(board);
		
		log.info("생성된 게시물의 번호 "+board.getBno());
	}
	
	@Test
	public void testGet() {
		boardService.get(1L);		
	}
	
	@Test
	public void testModify() {
		BoardVO board = boardService.get(1L);		
		
		if(board == null) return;
		 
		board.setTitle("제목 수정합니다.");		
		
		log.info("수정 결과 "+boardService.modify(board) );
	}
	
	@Test
	public void testRemove() {
		log.info("삭제 결과 "+boardService.remove(1L) );
	}
	
	
}
