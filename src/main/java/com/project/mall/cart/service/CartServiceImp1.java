package com.project.mall.cart.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mall.command.CartVO;

@Service("cartService")
public class CartServiceImp1 implements CartService {

	@Autowired
	private CartMapper cartMapper;

	@Override
	public int cartAdd(CartVO vo) {
		
		return cartMapper.cartAdd(vo);
	}
	//장바구니에 첫제품담으면 insert
	@Override
	public int getUserCartList(CartVO vo) {
		return cartMapper.getUserCartList(vo);	
	}
	//장바구니 동일제품 개수추가
	@Override
	public int updateCart(CartVO vo) {
		
		return cartMapper.updateCart(vo);
	}
	//장바구니 목록
	@Override
	public ArrayList<CartVO> getCartlist(String user_id) {
		
		return cartMapper.getCartlist(user_id);
	}
	@Override
	public int getTotalPrice(String user_id) {
		
		return cartMapper.getTotalPrice(user_id);
	}
	
	@Override
	public int updateQuantity(String userId, int boxcount, int product_no) {
	    
	    return cartMapper.updateQuantity(userId, boxcount, product_no);
	}
}
