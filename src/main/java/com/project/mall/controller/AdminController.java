package com.project.mall.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.mall.admin.service.AdminService;
import com.project.mall.command.ProductImageVO;
import com.project.mall.command.ProductVO;
import com.project.mall.command.UserVO;
import com.project.mall.util.page.Criteria;
import com.project.mall.util.page.PageVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	
	@RequestMapping("/adminMain")
	public String main(ProductVO vo, Model model, HttpSession session, Criteria cri) {
		
		UserVO uservo = (UserVO)session.getAttribute("userVO");
		
		System.out.println(uservo.toString());
		
		String user_id = uservo.getUserId();
		
		
		System.out.println(user_id);
		
		List<ProductVO> list = adminService.getList(user_id, cri);
		
		int total = adminService.getTotal(user_id);
		
		if(total > 0) {
			
			List<ProductImageVO> imageList = adminService.getProductImageList(list);
			model.addAttribute("imageList", imageList);
		}
		
		
		PageVO pageVO = new PageVO(cri,total);
		
		System.out.println(pageVO.toString());
		
		System.out.println(total);
		
		model.addAttribute("pagevo", pageVO);
		
		model.addAttribute("uservo", uservo);
		model.addAttribute("list", list);
		
		
		
		return "admin/adminMain";
	}
	
	
	@GetMapping("/adminDetail")
	public String detail(@RequestParam("product_no") int product_no, Model model, HttpSession session) {
		
		UserVO uservo = (UserVO)session.getAttribute("userVO");
		
		System.out.println(product_no);
		
		ProductVO vo = adminService.getDetail(product_no, uservo.getUserId());
		
		ProductImageVO imagevo = adminService.getProductImage(vo);
		
		System.out.println(vo.toString());
		
		model.addAttribute("vo", vo);
		model.addAttribute("imagevo", imagevo);
		
		
		return "admin/adminDetail";
	}
	
	
	
	@RequestMapping("/adminRegist")
	public String regist(Model model, HttpSession session) {
		
		UserVO uservo = (UserVO)session.getAttribute("userVO");
		
		model.addAttribute("user_id", uservo.getUserId());
		
		return "admin/adminRegist";
	}
	
	
	@PostMapping("/modifyForm")
	public String modifyForm(ProductVO vo) {
		
		System.out.println(vo.toString());
		
		adminService.updateDetail(vo);
		
		
		
		return "redirect:/admin/adminMain";
	}
	
	@PostMapping("/deleteForm")
	public String deleteForm(ProductVO vo) {
		
		adminService.deleteProduct(vo);
		
		
		return "redirect:/admin/adminMain";
	}
	
	@PostMapping("/registForm")
	public String registForm(@Valid ProductVO vo, @RequestParam("product_image") MultipartFile product_image, Errors errors) {
		
		System.out.println("이미지의 확장자는 : " + product_image.getContentType());
		
		if(errors.hasErrors()) {
			System.out.println("에러발생");
			return "redirect:/admin/adminMain";
		}
		
		
		adminService.registProduct(vo, product_image);
		
		return "redirect:/admin/adminMain";
		
	}
	
	
	
	
}
