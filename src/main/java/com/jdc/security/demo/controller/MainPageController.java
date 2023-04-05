package com.jdc.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.security.demo.model.service.ProductService;

@Controller
@RequestMapping
public class MainPageController {
	
	@Autowired
	private  ProductService productService;

	@GetMapping("/main")
	public String main(Authentication authentication, ModelMap model) {
		
		model.put("username", authentication.getName());
		model.put("products", productService.findAll());
		
		return "main.html";
	}
	
}
