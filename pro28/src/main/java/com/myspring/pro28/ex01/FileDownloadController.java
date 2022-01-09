package com.myspring.pro28.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*@Controller*/
public class FileDownloadController {
	private static String CURR_IMAGE_REPOT_PATH = "C:\\Temp\\temp_upload";	
	
	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse resp) throws IOException
	{
		OutputStream out = resp.getOutputStream();
		
		String downFile = CURR_IMAGE_REPOT_PATH+"\\"+imageFileName;
		
		File file = new File(downFile);
		
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-disposition", "attachment; fileName="+imageFileName);
		
		FileInputStream in = new FileInputStream(file);
		
		byte[] buffer = new byte[1024*8]; //8kb
		
		while(true){
			int count = in.read(buffer);
			if(count == -1 ) break;
			out.write(buffer, 0, count);
		}
		
		in.close();
		out.close();
	}
}
