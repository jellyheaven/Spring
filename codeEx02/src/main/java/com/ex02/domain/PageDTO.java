package com.ex02.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri ,int total) {
		this.total = total;
		this.cri = cri;
		
		//끝번호 구하기
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//시작번호
		this.startPage = this.endPage - 9;
		
		//총 끝번호
		int realEnd = (int) (Math.ceil( (total * 1.0) / cri.getAmount() ));
		
		//실제 끝번호가 작으면 
		if(realEnd < this.endPage) {
			this.endPage = realEnd;			
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < realEnd;	
		
//		System.out.println("this.startPage :"+this.startPage );
//		System.out.println("this.endPage :"+this.endPage );
//		System.out.println("this.prev :"+this.prev );
//		System.out.println("this.next :"+this.next );
//		System.out.println("this.total :"+this.total );
	}
}
