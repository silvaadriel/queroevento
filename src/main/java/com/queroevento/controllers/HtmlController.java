package com.queroevento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/admin")
	public String otherMapping() {
		return "admin";
	}
}