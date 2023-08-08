package com.project.mall.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.mall.command.ProductVO;
import com.project.mall.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")

	private ProductService productService;
	
	
	
	@GetMapping("/productlist")
	public String productlist(ProductVO vo, Model model,/*@RequestParam String searchName*/@RequestParam(value = "searchName", required = false, defaultValue = "") String searchName) {
		ArrayList<ProductVO> list = productService.getList(searchName);
		model.addAttribute("list",list);
		return "product/productlist";
	}
	

}
