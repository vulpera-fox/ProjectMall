package com.project.mall.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mall.admin.service.AdminService;
import com.project.mall.command.CategoryVO;

@RestController
public class AdminAjaxController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/getCategory")
	public ResponseEntity<ArrayList<CategoryVO>> getCategory() {
		
		System.out.println("작동했음");
		
		return new ResponseEntity<>(adminService.getCategory(), HttpStatus.OK);
	}

	@GetMapping("/getCategoryChild/{a}/{b}/{c}")
	public ResponseEntity<ArrayList<CategoryVO>> getCategoryChild(@PathVariable("a") String group_id,
																  @PathVariable("b") int category_lv,
																  @PathVariable("c") int category_detail_lv) {
		
		CategoryVO vo = CategoryVO.builder().group_id(group_id).category_lv(category_lv).category_detail_lv(category_detail_lv).build();
		
		
		return new ResponseEntity<>(adminService.getCategoryChild(vo), HttpStatus.OK);
	}
	
	@RequestMapping("/display")
	public byte[] display(@RequestParam("filePath") String filePath,
										  @RequestParam("fileName") String fileName,
										  @RequestParam("uuid") String uuid){
		
		
		File file = new File("/Users/uwu/eclipse-workspace/Spring/upload/" + filePath + "/" + uuid + "_" + fileName);
		
		byte[] arr = null;
		
		try {
			
			arr = FileCopyUtils.copyToByteArray(file);
			
			System.out.println(arr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	
}
