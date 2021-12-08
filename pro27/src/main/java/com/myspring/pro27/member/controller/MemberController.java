package com.myspring.pro27.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro27.member.vo.MemberVO;


public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO , HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView loing(@ModelAttribute("member") MemberVO memberVO, RedirectAttributes rAttr, HttpServletRequest req , HttpServletResponse resp) throws Exception;
}
