package com.ex02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ex02.domain.Criteria;
import com.ex02.domain.ReplyVO;

public interface ReplyMapper {
	
	public List<ReplyVO> getList();
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(ReplyVO vo);
	
	public List<ReplyVO> getListWithPageing(@Param("cri") Criteria cri ,@Param("bno") Long bno);
	
	public int getCountbyBno(Long bno);
}
