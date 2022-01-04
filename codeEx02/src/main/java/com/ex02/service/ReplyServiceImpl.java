package com.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex02.domain.Criteria;
import com.ex02.domain.ReplyPageDTO;
import com.ex02.domain.ReplyVO;
import com.ex02.mapper.BoardMapper;
import com.ex02.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
//@AllArgsConstructor 자동처리시 
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;

	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("======register===========");
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long bno) {
		log.info("======get===========");
		return mapper.read(bno);
	}

	
	@Override
	public int modify(ReplyVO vo) {
		log.info("======modify===========");
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long bno) {
		log.info("======remove===========");
		ReplyVO vo = mapper.read(bno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(bno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("======getList===========");
		return mapper.getListWithPageing(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("======getListPage===========");
		return new ReplyPageDTO(mapper.getCountbyBno(bno), mapper.getListWithPageing(cri, bno));
	}

}
