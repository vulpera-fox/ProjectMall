package com.project.mall.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.mall.command.CategoryVO;
import com.project.mall.command.ProductImageVO;
import com.project.mall.command.ProductVO;
import com.project.mall.util.page.Criteria;

public interface AdminService {
	
	public List<ProductVO> getList(String user_id, Criteria cri);
	
	public List<ProductVO> getSearchedList(String user_id, Criteria cri, String product_name);
	
	public ProductVO getDetail(int product_no, String user_id);
	
	public void updateDetail(ProductVO vo);
	
	public void deleteProduct(ProductVO vo);
	
	public int registProduct(ProductVO vo, MultipartFile product_image);
	
	public ArrayList<CategoryVO> getCategory();
	
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	public ProductImageVO getProductImage(ProductVO vo);
	
	public List<ProductImageVO> getProductImageList(List<ProductVO> list);
	
	public int getTotal(String user_id);
	
	public int getSearchedTotal(String user_id, String product_name);
	
	
}
