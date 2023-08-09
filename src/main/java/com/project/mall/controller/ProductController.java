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
import com.project.mall.util.page.Criteria;
import com.project.mall.util.page.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")

	private ProductService productService;
	
	
	

	@GetMapping("/productpage")
	public String productpage(ProductVO vo, Criteria cri, Model model ) {
		
		ArrayList<ProductVO> list = productService.getList(cri);
		int total = productService.getTotal(cri);
		
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		System.out.println(list.toString());
		System.out.println(pageVO.toString());

		
		return "product/productpage";
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(@RequestParam("product_no") int product_no
										   	  , Model model) {
		ProductVO vo = productService.getDetail(product_no);
		model.addAttribute("vo", vo);
	
		return "product/productDetail";
	}

}
