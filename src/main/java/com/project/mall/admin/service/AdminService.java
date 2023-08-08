package com.project.mall.admin.service;

import java.util.List;

import com.project.mall.command.ProductVO;

public interface AdminService {
	
	public List<ProductVO> getList(String user_id);
	
	public ProductVO getDetail(int product_no, String user_id);
	
}
