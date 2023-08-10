package com.project.mall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.mall.admin.service.AdminService;
import com.project.mall.command.ProductImageVO;
import com.project.mall.command.ProductVO;
import com.project.mall.command.UserVO;
import com.project.mall.product.service.ProductService;
import com.project.mall.util.page.Criteria;
import com.project.mall.util.page.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	
	@Autowired
	private AdminService adminService;
	


	@GetMapping("/productpage")
	public String productpage(ProductVO vo, Criteria cri, Model model ) {
		
		ArrayList<ProductVO> list = productService.getList(cri);
		int total = productService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		System.out.println(pageVO.toString());
		System.out.println(cri);
		
		List<ProductImageVO> imageList = adminService.getProductImageList(list);
		model.addAttribute("imageList", imageList);

		return "product/productpage";
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(@RequestParam("product_no") int product_no
										   	  , Model model,
										   	  HttpSession session) {
		UserVO uservo = (UserVO)session.getAttribute("userVO");
		ProductVO vo = productService.getDetail(product_no);
		ProductImageVO imagevo = adminService.getProductImage(vo);
		
		model.addAttribute("vo", vo);
		model.addAttribute("imagevo", imagevo);
	
		return "product/productDetail";
	}

}
