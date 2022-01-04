package com.ex02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ex02.domain.BoardVO;
import com.ex02.domain.Criteria;

public interface BoardMapper {

//	@Select("SELECT BNO, TITLE, CONTENT, WRITER, REGDATE, UPDATEDATE FROM TBL_BOARD WHERE BNO > 0 ")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPage(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int update(BoardVO board);
	
	public int delete(Long bno);
	
	public int getTotalCount(Criteria cri);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
