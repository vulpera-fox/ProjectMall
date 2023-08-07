package com.project.mall.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.mall.command.ProductVO;

@Mapper
public interface ProductMapper {
	public ArrayList<ProductVO> getList(String searchName);
	public ArrayList<ProductVO> kewordSerach();
}
