package com.project.mall.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.mall.command.OrderVO;
import com.project.mall.command.ProductVO;
import com.project.mall.order.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;

	//주문하기 페이지
	@GetMapping("/order")
	public String order(@RequestParam("product_no") String product_no, Model model) {
		ProductVO productVO = orderService.productInfo(product_no);
		model.addAttribute("productVO",productVO);
		
		return "order/order";
	}
	//주문완료 하고 어디론가 이동
	@PostMapping("/orderForm")
	public String orderForm(@ModelAttribute OrderVO vo, RedirectAttributes ra,@RequestParam("product_no") int product_no) {
		int result = orderService.insertOrderList(vo);
		if(result == 1 ) {
			orderService.orderFinish(product_no);
			ra.addFlashAttribute("msg","주문이 완료되었습니다");
		}
		System.out.println(vo.toString());
		return "redirect:/product/productlist";
	}
	
	//주문내역
	@GetMapping("/orderlist")
	public String orderlist(Model model,@RequestParam("user_id") String user_id) {
		ArrayList<OrderVO> list = orderService.getOrderList(user_id);
		model.addAttribute("list",list);
		return "order/orderlist";
	}
}