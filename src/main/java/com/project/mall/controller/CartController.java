package com.project.mall.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.mall.cart.service.CartService;
import com.project.mall.command.CartVO;

@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	@Qualifier("cartService")
	private CartService cartService;
	
	@GetMapping("/cart")
	public String cart() {
		
		return "cart/cart";
	}
	//장바구니에 담기
	@PostMapping("/getUserCartList")
	public String getUserCartList(@ModelAttribute CartVO vo) {
		int result = cartService.getUserCartList(vo);
		if(result == 0) {
			cartService.cartAdd(vo);			
		}else {
			cartService.updateCart(vo);
		}
		return "redirect:/product/productlist";
	}
	
	@GetMapping("/cartlist")
	public String cartlist(Model model,@RequestParam("user_id") String user_id) {
		ArrayList<CartVO> list = cartService.getCartlist(user_id); 
		model.addAttribute("list",list);
		return "cart/cartlist";
	}
	
	@GetMapping("/getTotalPrice")
	public ResponseEntity<?> getTotalPrice(@RequestParam("user_id") String user_id) {
	    try {
	        int totalPrice = cartService.getTotalPrice(user_id);
	        return ResponseEntity.ok(Collections.singletonMap("totalPrice", totalPrice));
	    } catch(Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/updateQuantity")
	public ResponseEntity<Map<String, Integer>> updateQuantity(@RequestBody Map<String, String> request) {
	    String userId = request.get("userId");
	    int boxcount = Integer.parseInt(request.get("boxcount"));
	    int product_no = Integer.parseInt(request.get("product_no")); 
	    
	    int updatedRows = cartService.updateQuantity(userId, boxcount, product_no);
	    
	    Map<String, Integer> response = new HashMap<>();
	    response.put("updatedRows", updatedRows);
	    
	    return ResponseEntity.ok(response);
	}
}
