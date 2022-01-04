package com.ex02.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Setter;

@Data
public class MemberVO {
	private String userid;
	private String userpw;
	private String userName;
	private boolean enable;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
