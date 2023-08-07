package com.project.mall.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.mall.command.ProductVO;
import com.project.mall.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private	ProductService productService;
	
	@GetMapping("/productpage")
	public String productpage(@ModelAttribute("list") ProductVO vo) {
	
		
		return "product/productpage";
		
	}

}
