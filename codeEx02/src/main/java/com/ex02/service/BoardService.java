package com.ex02.service;

import java.util.List;

import com.ex02.domain.BoardAttachVO;
import com.ex02.domain.BoardVO;
import com.ex02.domain.Criteria;

public interface BoardService {
	
	//public List<BoardVO> getList();
	//목록
	public List<BoardVO> getList(Criteria cri);
	//등록
	public void register(BoardVO board);
	//특정정보
	public BoardVO get(Long bno);
	//수정
	public boolean modify(BoardVO board);
	//삭제
	public boolean remove(Long bno);
	//총갯수
	public int getTotal(Criteria cri);
	//첨부파일 리스트
	public List<BoardAttachVO> getAttachList(Long bno);
	
}
