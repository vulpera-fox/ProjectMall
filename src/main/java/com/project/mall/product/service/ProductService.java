 package com.project.mall.product.service;

import java.util.ArrayList;

import com.project.mall.command.ProductVO;
import com.project.mall.util.page.Criteria;

public interface ProductService {

	public ArrayList<ProductVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public ProductVO getDetail(int product_no);

}
