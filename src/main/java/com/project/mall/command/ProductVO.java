package com.project.mall.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
	
	private Integer product_no;
	private String product_name;
	private Integer product_price;
	private String product_description;
	private Integer product_soldcount;
	private LocalDateTime product_regdate;
	private String image_no;
	private String category_no;
	private String user_id;
}
