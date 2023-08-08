package com.project.mall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.mall.admin.service.AdminService;
import com.project.mall.command.ProductVO;
import com.project.mall.command.UserVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	String user_id = "admin";

	
	@RequestMapping("/adminMain")
	public String main(ProductVO vo, Model model, HttpSession session) {
		
		
		UserVO uservo = (UserVO)session.getAttribute("vo");
		
//		String user_id = uservo.getUserId();
		
		System.out.println(user_id);
		
		List<ProductVO> list = adminService.getList(user_id);
		
		model.addAttribute("uservo", uservo);
		model.addAttribute("list", list);
		
		
		return "admin/adminMain";
	}
	
	
	@GetMapping("/adminDetail")
	public String detail(@RequestParam("product_no") int product_no, Model model, HttpSession session) {
		
		UserVO uservo = (UserVO)session.getAttribute("vo");
		
		System.out.println(product_no);
		
		ProductVO vo = adminService.getDetail(product_no, user_id);
		
		System.out.println(vo.toString());
		
		model.addAttribute("vo", vo);
		
		
		return "admin/adminDetail";
	}
	
	
	
	@RequestMapping("/adminRegist")
	public String regist() {
		
		
		
		return "admin/adminRegist";
	}
	
	
	@PostMapping("/modifyForm")
	public String modifyForm(ProductVO vo) {
		
		
		return null;
	}
	
	
	
	
}
