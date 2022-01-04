package com.ex02.service;

import java.util.List;

import com.ex02.domain.Criteria;
import com.ex02.domain.ReplyPageDTO;
import com.ex02.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long bno);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long bno);
	
	public List<ReplyVO> getList(Criteria cri , Long bno);
	
	public ReplyPageDTO getListPage(Criteria cri , Long bno);
}
