package com.myspring.pro28.ex05;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("localController")
public class LocaleController {
	
	@RequestMapping(value="/test/locale.do", method = {RequestMethod.GET})
	public String locale(HttpServletRequest req , HttpServletResponse resp) {
		System.out.println("localeController 입니다.");
		return "locale";
	}
}
