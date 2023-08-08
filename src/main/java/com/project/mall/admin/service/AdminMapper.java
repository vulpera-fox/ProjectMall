package com.project.mall.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.mall.command.ProductVO;

@Mapper
public interface AdminMapper {
	
	public List<ProductVO> getList(String user_id);
	
	public ProductVO getDetail(@Param("product_no") int product_no, @Param("user_id") String user_id);

}
