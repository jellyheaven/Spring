package com.ex02.mapper;

import java.util.List;

import com.ex02.domain.BoardAttachVO;

public interface BoardAttachMapper {

	//첨부파일정보추가
	public void insert(BoardAttachVO vo);
	
	//첨부파일 일부 삭제
	public void delete(String uuid);
	
	//첨부파일 목록
	public List<BoardAttachVO> findByBno(Long bno);
		
	//첨부파일 전체 삭제
	public void deleteAll(Long bno);
}
