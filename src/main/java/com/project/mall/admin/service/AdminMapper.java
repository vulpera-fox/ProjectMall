package com.project.mall.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.project.mall.command.CategoryVO;
import com.project.mall.command.ProductImageVO;
import com.project.mall.command.ProductVO;

@Mapper
public interface AdminMapper {
	
	public List<ProductVO> getList(String user_id);
	
	public ProductVO getDetail(@Param("product_no") int product_no, @Param("user_id") String user_id);
	
	public void updateDetail(ProductVO vo);

	public void deleteProduct(ProductVO vo); 
	
	public int registProduct(ProductVO vo);
	
	
	public void registProductImage(ProductImageVO vo);
	
	public ArrayList<CategoryVO> getCategory();
	
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	public List<ProductImageVO> getProductImageList(List<ProductVO> list);
	
	public int getTotal(String user_id);

}
