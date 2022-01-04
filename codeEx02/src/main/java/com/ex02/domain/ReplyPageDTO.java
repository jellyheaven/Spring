package com.ex02.domain;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
public class ReplyPageDTO {

	private int replyCnt;
	private List<ReplyVO> list;
	
	public ReplyPageDTO() {
		
	}
	
	public ReplyPageDTO(int replyCnt, List<ReplyVO> list) {
		this.replyCnt = replyCnt;
		this.list = list;
	}
	
	
}
