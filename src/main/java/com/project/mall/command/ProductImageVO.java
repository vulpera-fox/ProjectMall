package com.project.mall.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageVO {
	
	private Integer upload_no;
	private String fileName;
	private String filePath;
	private String uuid;
	private LocalDateTime regdate;
	private Integer product_no;
	private String user_id;

}
