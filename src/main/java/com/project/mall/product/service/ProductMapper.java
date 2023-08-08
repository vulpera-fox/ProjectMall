package com.project.mall.product.service;

import java.util.ArrayList;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.mall.command.ProductVO;
import com.project.mall.util.page.Criteria;

@Mapper
public interface ProductMapper {
	public ArrayList<ProductVO> getList(@Param("searchName") String searchName,
										@Param("cri") Criteria cri);
	public ArrayList<ProductVO> kewordSerach();
	public int getTotal(Criteria cri);
}
