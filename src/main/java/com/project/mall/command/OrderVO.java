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
public class OrderVO {
	private int ordernumber; //주문번호
	private int product_no; //productVO 상품번호
	private String user_id; //UserVO 회원아이디
	private int boxnumber; //CartVO 장바구니번호
	private String category_id; //ProductVO 카테고리아이디
	private String paymentmethod;  //PaymentVO 결제수단
	private String ordername; //UserVO 주문자이름
	private String orderaddress; //UserVO 주소
	private int orderphone; //UserVO 번호
	private String orderdetailaddress; //상세주소
	private String ordermemo; //요구사항
	private LocalDateTime orderdate; //주문날자
	
}