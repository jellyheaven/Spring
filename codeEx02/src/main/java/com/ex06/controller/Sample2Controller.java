package com.ex06.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample2/*")
@Controller
public class Sample2Controller {
	
	@GetMapping("/all")
	public void doAll() {
		log.info("do all can access everbody");
	}
	
	@GetMapping("/member")
	public void doMember() {
		log.info("logined member");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("admin only");
	}
	
	//애노테이션 이용하는 스프링 시큐리티
	/*
	 * @PreAuthorize("hasAnyRole('ROLE_ADMIN, 'ROLE_MEMBER')")
	 * 
	 * @GetMapping("/annoMember") public void doMember2() {
	 * log.info("logined annotation member"); }
	 * 
	 * @Secured({"ROLE_ADMIN"})
	 * 
	 * @GetMapping("/annoAdmin") public void doAdmin2() {
	 * log.info("admin annotation only"); }
	 */
}
