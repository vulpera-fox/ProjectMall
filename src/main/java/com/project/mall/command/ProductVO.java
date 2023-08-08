package com.project.mall.command;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {

	private int product_no;
	private String product_name;
	private int product_price;
	private int product_stock;
	private String product_description;
	private int product_soldcount;
	private LocalDateTime product_regdate;
	private String image_no;
	private String category_no;
	private String user_id;
	private String CATEGORY_NAV;

}