package com.myspring.pro28.ex01;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	private static String CURR_IMAGE_REPOT_PATH = "C:\\Temp\\temp_upload";
	
	@RequestMapping(value = "/form")
	public String form(){
		return "uploadForm";	
	}
	
	@RequestMapping(value = "/upload" , method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartReq, HttpServletResponse resp) throws IOException {
		
		multipartReq.setCharacterEncoding("utf-8");
		
		Enumeration enu = multipartReq.getParameterNames();
		
		Map map = new HashMap<String, String>();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartReq.getParameter(name);
			map.put(name, value);
		}
		
		List fileList = fileProcess(multipartReq);		
		map.put("fileList", fileList);		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("result");
		return mav;
		
	}

	private List fileProcess(MultipartHttpServletRequest multipartReq) throws IOException {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartReq.getFileNames();
		
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mfile = multipartReq.getFile(fileName); //파일 이름에 대한 MultipartFile 가져옴.
			String orginFileName = mfile.getOriginalFilename();  //실제 파일이름 가져옴.
			fileList.add(orginFileName);
			File file = new File(CURR_IMAGE_REPOT_PATH+"\\"+fileName);
			
			if( mfile.getSize() != 0 ) {
				if(!file.exists()) {
					if(file.getParentFile().mkdir()) {
						file.createNewFile();
					}
				}
				mfile.transferTo(new File(CURR_IMAGE_REPOT_PATH+"\\"+orginFileName));
			}
		}		
		return fileList;
	}
	
	
}
