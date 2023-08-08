package com.project.mall.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {
	private int boxnumber; //장바구니번호
	private int boxcount; //물품개수
	private String product_no; //제품번호
	private String user_id; //구매자 아이디
	private String category_id; //카테고리아이디
	private String product_name; //상품이름
	private int product_price; //상품가격
	
	
}
