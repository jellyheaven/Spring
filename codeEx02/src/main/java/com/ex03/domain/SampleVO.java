package com.ex03.domain;

import lombok.Data;

@Data
public class SampleVO {
	
	private Integer mno;
	private String firstName;
	private String lastName;
	
	public SampleVO() {
		super();
	}
	
	public SampleVO(Integer mno, String firstName, String lastName) {
		super();
		this.mno = mno;
		this.firstName = firstName;
		this.lastName = lastName;
	}	
}
