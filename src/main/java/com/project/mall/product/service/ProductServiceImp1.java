package com.project.mall.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mall.command.ProductVO;

@Service("productService")
public class ProductServiceImp1 implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public ArrayList<ProductVO> getList(String searchName) {
		
		return productMapper.getList(searchName);
	}

	@Override
	public ArrayList<ProductVO> kewordSerach() {
		
		return productMapper.kewordSerach();
	}

}
