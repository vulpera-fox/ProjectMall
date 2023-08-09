package com.project.mall.command;

import java.util.ArrayList;
import java.util.List;

public class OrderVOListWrapper {
	private List<OrderVO> orderList;

    // 기본 생성자
    public OrderVOListWrapper() {
        orderList = new ArrayList<>();
    }

    // getter
    public List<OrderVO> getOrderList() {
        return orderList;
    }

    // setter
    public void setOrderList(List<OrderVO> orderList) {
        this.orderList = orderList;
    }
}
