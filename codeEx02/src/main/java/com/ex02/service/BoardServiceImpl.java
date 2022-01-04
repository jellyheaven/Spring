package com.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex02.domain.BoardAttachVO;
import com.ex02.domain.BoardVO;
import com.ex02.domain.Criteria;
import com.ex02.mapper.BoardAttachMapper;
import com.ex02.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor 자동처리시 
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = {@Autowired})
	private BoardAttachMapper attachMapper;
	
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList......:" );
//		return boardMapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with Criteria......:"+ cri);
		return boardMapper.getListWithPage(cri);
	}

	@Override
	public void register(BoardVO board) {
		log.info("register......:"+board );
		boardMapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) { return; }
		
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
		
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get......:"+bno );
		return boardMapper.read(bno);
	}

	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......:"+board );
		
		attachMapper.deleteAll(board.getBno());
		
		boolean result_check = false;
		
		int cnt = boardMapper.update(board);
		
		if(cnt == 1) result_check = true;
		
		if(result_check && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return result_check;
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove......:"+bno );
		
		attachMapper.deleteAll(bno);
		
		return boardMapper.delete(bno) == 1;
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal......:"+cri );
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		log.info("get Attach list by bno :"+bno);

		return attachMapper.findByBno(bno);
	}

	

}
