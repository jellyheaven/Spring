package com.spring.member.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	private MemberService memberService;
	
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	//목록
	@Override
	public ModelAndView listMembers(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		
		String viewName = getViewName(req);
		
		List<MemberVO> memberslist = memberService.listMembers();
		
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.addObject("membersList",memberslist);
		
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO memberVO = new MemberVO();
		
		/*
		MemberVO memberVO = new MemberVO();
		String id = req.getParameter("id"); 
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name"); 
		String email = req.getParameter("email");
		*/

		
		bind(req, memberVO); //memberVO 속성 자동으로 설정
		
		int result = 0;
		
		result = memberService.addMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		return mav;
	}

	@Override
	public ModelAndView removeMember(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		memberService.removeMember(id);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		return mav;
	}
	
	
	public ModelAndView form(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String viewName = getViewName(req);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	//요청한 동일한 뷰명 반환
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}

}
