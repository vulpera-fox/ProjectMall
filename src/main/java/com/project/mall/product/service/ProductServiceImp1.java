package com.project.mall.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mall.command.ProductVO;
import com.project.mall.util.page.Criteria;

@Service("productService")
public class ProductServiceImp1 implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public ArrayList<ProductVO> getList() {
		
		return productMapper.getList();
	}

	@Override
	public ArrayList<ProductVO> kewordSerach() {
		
		return productMapper.kewordSerach();
	}

	@Override
	public int getTotal(Criteria cri) {
		return productMapper.getTotal(cri);
	}

	@Override
	public ProductVO getDetail(int product_no) {
		
		return productMapper.getDetail(product_no);
	}

}
