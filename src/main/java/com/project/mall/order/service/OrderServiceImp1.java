package com.project.mall.order.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mall.command.OrderVO;
import com.project.mall.command.ProductVO;

@Service("orderService")
public class OrderServiceImp1 implements OrderService{
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public ProductVO productInfo(String product_no) {
		
		return orderMapper.productInfo(product_no);
	}

	@Override
	public int insertOrderList(OrderVO vo) {
		
		return orderMapper.insertOrderList(vo);
	}

	@Override
	public void orderFinish(int product_no) {
		orderMapper.orderFinish(product_no);
		
	}
	//주문내역 조회
	@Override
	public ArrayList<OrderVO> getOrderList(String user_id) {
		
		return orderMapper.getOrderList(user_id);
	}

}
