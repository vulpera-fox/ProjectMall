package com.project.mall.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mall.command.ProductVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminMapper adminMapper;

	@Override
	public List<ProductVO> getList(String user_id) {
		return adminMapper.getList(user_id);
	}

	@Override
	public ProductVO getDetail(int product_no, String user_id) {
		return adminMapper.getDetail(product_no, user_id);
	}

}
