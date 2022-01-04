package com.ex02.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ex05.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload Form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\Temp";
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("=============================");
			log.info("Upload File Name: "+multipartFile.getOriginalFilename());
			log.info("Upoload File Size :" + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			
		}//end for
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload Ajax");
	}
	
//	@PostMapping("/uploadAjaxAction")
//	public void uploadAjaxAction(MultipartFile[] uploadFile) {
//		log.info("update ajax post...");
//		
//		String uploadFolder = "C:\\Temp";
//		
//		//make folder
//		File uploadPath = new File(uploadFolder, getFolder());
//		log.info("uploadPath:"+uploadPath);
//		
//		if(uploadPath.exists() == false) {
//			uploadPath.mkdirs();
//		}
//		
//		for (MultipartFile multipartFile : uploadFile) {
//			log.info("=============================");
//			log.info("Upload File Name: "+multipartFile.getOriginalFilename());
//			log.info("Upoload File Size :" + multipartFile.getSize());
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			
//			//IE 파일 경로
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
//			
//			log.info("only file name:"+ uploadFileName);
//			
//			
//			//중복방지 UUID 적용
//			UUID uuid = UUID.randomUUID();
//			
//			uploadFileName = uuid.toString()+"_"+uploadFileName;
//			
//			//File saveFile = new File(uploadFolder, uploadFileName);
//			File saveFile = new File(uploadPath, uploadFileName);		
//			
//			
//			try {
//				multipartFile.transferTo(saveFile);
//				
//				//썸네일 생성
//				if(checkImageType(saveFile)) {
//					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName) );
//					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
//					thumbnail.close();
//				}
//				
//			} catch (IllegalStateException e) {
//				log.error(e.getMessage());
//			} catch (IOException e) {
//				log.error(e.getMessage());
//			}
//			
//		}
//	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/uploadAjaxAction" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile){
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		
		String uploadFolder = "C:\\Temp";
		
		String uploadFolderPath = getFolder();
		
		//make folder
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		log.info("uploadPath:"+uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			attachDTO.setFileName(uploadFileName);
			
			//IE 파일 경로
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			log.info("only file name:"+ uploadFileName);			
			
			
			
			//중복방지 UUID 적용
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString()+"_"+uploadFileName;			
			
			File saveFile = new File(uploadPath, uploadFileName);		
			
			
			try {
				multipartFile.transferTo(saveFile);
				
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				//썸네일 생성
				if(checkImageType(saveFile)) {
					
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName) );
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				
				//add list
				list.add(attachDTO);
				
			} catch (IllegalStateException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			
		}//end for
		
		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
	}
	
	//이미지 
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("fileName: "+fileName);
		
		File file = new File("C:\\Temp\\"+fileName);
		
		log.info("file:"+file);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//다운로드
	@GetMapping(value = "/download" , produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent,  String fileName){
		log.info("download file:"+fileName);
		
		Resource resource = new FileSystemResource("C:\\Temp\\"+fileName);
		
		if(resource.exists() == false) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		log.info("resource : " + resource);
		
		String resourceName = resource.getFilename();
		
		//remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			
			//브라우저 제품별 다운로드 구분 
			if(userAgent.contains("Trident")) {
				log.info("MSIE , Trident  IE11 vesion");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replaceAll("\\+", " ");
			}else if(userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8");
			}else {
				log.info("Chrome browser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			log.info("downloadName : "+downloadName);
			
			headers.add("Content-Disposition", "attachment;filename="+downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile"+fileName);
		
		try {
			File file = new File("C:\\Temp\\"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName:"+largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	//년월일 폴더 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	//이미지 타입 체크 
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			log.info("contentType : "+contentType);
			
			return contentType.startsWith("image");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
