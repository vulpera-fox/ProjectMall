package com.project.mall.cart.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.mall.command.CartVO;

@Mapper
public interface CartMapper {
	public int getUserCartList(CartVO vo ); //장바구니에 있는지 없는지 검색
	public int cartAdd(CartVO vo); //장바구니 추가
	public int updateCart(CartVO vo); //장바구니 상품개수추가
	public ArrayList<CartVO> getCartlist(String user_id); //장바구니 목록보기
	public int getTotalPrice(String user_id);  //장바구니 물건  총 가격
	public int updateQuantity(String userId,int boxcount, int product_no);
}
