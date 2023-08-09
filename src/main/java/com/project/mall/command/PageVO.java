package com.project.mall.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import lombok.Data;

@Data
public class PageVO {
	
	private int start; // 시작페이지의 번호
	private int end; // 끝페이지의 번호
	private boolean prev; // 이전 버튼 활성화 여부
	private boolean next; // 다음 버튼 활성화 여부
	private int total; // 전체 게시글 갯수
	private int realEnd; // 실제 보여지는 끝번호
	
	private int page; // criteria에 있는 현재 조회 페이지
	private int amount; // criteria에 있는 데이터 갯수
	private Criteria criteria; // 페이지기준
	
	private int pnCount = 10; // 페이지네이션 갯수
	
private List<Integer> pageList; // 페이지네이션을 리스트로 저장
	
	// 페이지네이션 클래스는 cri와 total을 매개변수로 받음
	public PageVO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.page = criteria.getPage();
		this.amount = criteria.getAmount();
		this.total = total;
		
		// end페이지 계산
		// page가 1일때, end = 10
		// page가 11일때, end = 20
//		this.end = (int)(Math.ceil( 현재 조회하는 페이지 / 페이지네이션의 갯수 )) * 페이지네이션의 갯수;
		this.end = (int)(Math.ceil(this.page / (double)this.pnCount)) * 10;
		
		// start페이지 계산
//		this.start = this.end - 페이지네이션의 갯수 + 1;
		this.start = this.end - this.pnCount + 1;
		
		// 실제 끝번호의 계산
		// 총 게시글 수가 53개라면, 마지막 번호는 6
		// 총 게시글 수가 232개라면, 마지막 번호는 24
//		this.realEnd = (int)(Math.ceil( 전체 게시글 수 / 데이터의 갯수 ));
		this.realEnd = (int)(Math.ceil(this.total / (double)this.amount));
		
		// end페이지의 재결정
		// 데이터가 25개 있을 때 end = 10, realEnd = 3 -> 실제끝번호를 따라감
		// 데이터가 153개 있을 때 end = 20, realEnd = 16 - > 실제끝번호를 따라감
		// 데이터가 153개 있을 때 3번페이지를 조회하면 end = 10, realEnd = 16 -> 페이지네이션을 따라감
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		// prev 버튼 활성화
		// start페이지는 1, 11, 21 ...
		this.prev = this.start > 1;
		
		// next 버튼 활성화
		this.next = this.realEnd > this.end;
		
		// 타임리프 - 리스트에 페이지네이션을 담음
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
			
}

}
