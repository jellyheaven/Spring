package com.ex02.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ex02.domain.Criteria;
import com.ex02.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	//테스트 전에 게시물이 존재하는지 확인
	private Long[] bnoArr = {148L, 147L, 146L, 145L, 144L};
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testGetList() {
		log.info("===Mapper testGetReplyList====");
		mapper.getList().forEach(replay -> log.info(replay));
		
	}
	
	@Test
	public void testInsert() {
		log.info("===Mapper testInsert====");
		
		IntStream.rangeClosed(1, 10).forEach(i ->{
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트 "+ i);
			vo.setReplyer("replayer" + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		log.info("==Mapper testRead====");
		Long targetRno = 5L;
		ReplyVO vo = mapper.read(targetRno);
		
		log.info("result value : " + vo);
	}
	
	@Test
	public void testDelete() {
		log.info("===Mapper testDelete====");
		
		Long targetRno = 30L;
		mapper.delete(targetRno);
		
	}
	
	@Test
	public void testUpdate() {
		log.info("===Mapper testUpdate====");
		Long targetRno = 108L;
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update Reply");
		
		int count = mapper.update(vo);
		
		log.info("update count : "+ count);
	}
	
	@Test
	public void getListWithPageing() {
		log.info("===Mapper getListWithPageing 148L====");
		Criteria cri = new Criteria();
		List<ReplyVO> replyList = mapper.getListWithPageing(cri, bnoArr[0]);
		replyList.forEach(replay -> log.info(replay));
		
	}
	
	@Test
	public void getListWithPageing2() {
		log.info("===Mapper getListWithPageing2 148L====");
		Criteria cri = new Criteria(2,10);
		List<ReplyVO> replyList = mapper.getListWithPageing(cri, bnoArr[0]);
		replyList.forEach(replay -> log.info(replay));
	}
}
