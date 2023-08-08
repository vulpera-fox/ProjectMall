package com.project.mall.order.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.mall.command.OrderVO;
import com.project.mall.command.ProductVO;

@Mapper
public interface OrderMapper {
	public ProductVO productInfo(String product_no);
	public int insertOrderList(OrderVO vo); //주문하고 주문내역 저장 //단일 바로구매 기능
	public void orderFinish(int product_no); //주문하고 상품 재고랑 판매개수 올리기
	public ArrayList<OrderVO> getOrderList(String user_id); //주문내역 조회
}
