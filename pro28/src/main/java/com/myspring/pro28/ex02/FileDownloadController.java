package com.myspring.pro28.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPOT_PATH = "C:\\Temp\\temp_upload";
	
	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse resp) throws IOException {
//		OutputStream out = resp.getOutputStream();
//		
//		String filePath = CURR_IMAGE_REPOT_PATH +"\\"+imageFileName;
//		
//		File image = new File(filePath);
//		
//		int lastIndex = imageFileName.lastIndexOf(".");
//		String fileName = imageFileName.substring(0,lastIndex);
//		
//		File thumbnail = new File(CURR_IMAGE_REPOT_PATH+"\\thumbnail\\"+fileName+".png");
//		
//		if(image.exists()) {
//			thumbnail.getParentFile().mkdir();
//			Thumbnails.of(image).size(50, 50).outputFormat("png").toFile(thumbnail);
//		}
//		
//		FileInputStream in= new FileInputStream(thumbnail);
//		
//		byte[] buffer = new byte[1024*8];
//		
//		while(true) {
//			int count = in.read(buffer);
//			if(count == -1) break;
//			out.write(buffer, 0, count);
//		}
//		
//		in.close();
//		out.close();
		
		//바로 출력 하기 
		OutputStream out = resp.getOutputStream();
		
		String filePath = CURR_IMAGE_REPOT_PATH +"\\"+imageFileName;
		
		File image = new File(filePath);
		
		if(image.exists()) {
			Thumbnails.of(image).size(50, 50).outputFormat("png").toOutputStream(out);
		}
		
		byte[] buffer = new byte[1024*8];
		out.write(buffer);
		
		out.close();
	}
}
