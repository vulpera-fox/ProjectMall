package com.project.mall.order.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.project.mall.command.OrderVO;
import com.project.mall.command.ProductVO;

public interface OrderService {
	public ProductVO productInfo(String product_no);
	public int insertOrderList(OrderVO vo);
	public void orderFinish(int product_no); //주문하고 상품 재고랑 판매개수 올리기
	public ArrayList<OrderVO> getOrderList(String user_id); //주문내역 조회
	public void deleteFinish(int product_no); //구매한제품 장바구니에서 제거
}
