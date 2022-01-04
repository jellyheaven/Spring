package com.ex02.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ex02.domain.BoardAttachVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardAttachMapperTests {
	@Setter(onMethod_ = {@Autowired})
	private BoardAttachMapper mapper;
	
	@Test
	public void testInsert() {
		BoardAttachVO vo = new BoardAttachVO();
		vo.setUuid("12312312313222");
		vo.setUploadPath("업로드 경로");
		vo.setFileName("파일명 ");
		vo.setFileType(false);
		vo.setBno(121L);
		
		mapper.insert(vo);
		
		log.info(vo);
	}
	
	@Test 
	public void testDelete(){ 
		mapper.delete("12312312313"); 
	}
	
	@Test 
	public void testfindByBno(){
		List<BoardAttachVO> attachlist = mapper.findByBno(144L); 
		attachlist.forEach(attch -> log.info(attch));
	}
}
