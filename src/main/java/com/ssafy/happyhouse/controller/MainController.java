package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MainController {

	@GetMapping("")
	public String goMain() {
		return "deal/main";
	}

}
