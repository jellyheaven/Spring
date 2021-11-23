package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {
	
	/*
	 * public ModelAndView login(HttpServletRequest req, HttpServletResponse resp)
	 * throws Exception { String userID = ""; String passwd = ""; ModelAndView mav =
	 * new ModelAndView(); req.setCharacterEncoding("utf-8"); userID =
	 * req.getParameter("userID"); passwd = req.getParameter("passwd");
	 * 
	 * mav.addObject("userID", userID); mav.addObject("passwd", passwd);
	 * mav.setViewName("result"); return mav; }
	 */
	//요청명과 동일한  JSP 표시 하기 
	public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userID = "";
		String passwd = "";
		ModelAndView mav = new ModelAndView();
		req.setCharacterEncoding("utf-8");
		userID = req.getParameter("userID");
		passwd = req.getParameter("passwd");
		
		String viewName = getViewName(req);
		
	
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	private String getViewName(HttpServletRequest req) throws Exception {
		String contextPath = req.getContextPath();
		System.out.println("contextPath : "+contextPath);
		String uri = (String) req.getAttribute("javax.servlet.include.request_uri");
		
		if(uri == null || uri.trim().equals("")) {
			uri = req.getRequestURI();
			System.out.println("uri : "+uri);
		}
		
		int begin = 0;
		if( !((contextPath == null) || ("".equals(contextPath))) ) {
			begin = contextPath.length();
			System.out.println("begin : "+begin);
		}
		
		int end;
		if(uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		}else if(uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		}else {
			end = uri.length();
		}
		
		String fileName = uri.substring(begin, end);
		
		if(fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			
			System.out.println("find . : "+fileName);
			
		}
		
		if(fileName.indexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
			
			System.out.println("find / : "+fileName);
		}
		
		System.out.println("fileName : "+fileName);
		return fileName;
	}

	public ModelAndView memberInfo(HttpServletRequest req, HttpServletResponse response) throws Exception {
		req.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		
		String id=req.getParameter("id"); 
		String pwd=req.getParameter("pwd"); 
		String name=req.getParameter("name"); 
		String email=req.getParameter("email"); 
		
		mav.addObject("id",id);
		mav.addObject("pwd",pwd);
		mav.addObject("name",name);
		mav.addObject("email",email);
		
		mav.setViewName("memberInfo");
		
		return mav;
		
	}
}
