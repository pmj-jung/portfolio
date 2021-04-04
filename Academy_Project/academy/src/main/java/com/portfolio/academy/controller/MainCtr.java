package com.portfolio.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainCtr {

	// 메인페이지
	@RequestMapping(value =  {"/main.do","/"})
	public String getMain() {
		return "main";
	}
}
