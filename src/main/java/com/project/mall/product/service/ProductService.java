 package com.project.mall.product.service;

import java.util.ArrayList;

import com.project.mall.command.ProductVO;
import com.project.mall.util.page.Criteria;

public interface ProductService {

	public ArrayList<ProductVO> getList();
	public ArrayList<ProductVO> kewordSerach();
	public int getTotal(Criteria cri);

}
