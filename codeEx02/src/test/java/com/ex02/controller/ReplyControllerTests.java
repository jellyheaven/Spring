package com.ex02.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class ReplyControllerTests {
	@Setter(onMethod_ = {@Autowired} )
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testCreate() throws Exception {
		log.info("==testCreate==");
		String s_cont = "{\"bno\":145,\"reply\":\"Hello Reply\",\"replyer\":\"user00\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/replies/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(s_cont))
				.andExpect(status().is(200));
	}
	
	@Test
	public void testremove() throws Exception {
		log.info("==testremove==");		
		mockMvc.perform(MockMvcRequestBuilders.delete("/replies/21")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}
	
	@Test
	public void testmodify() throws Exception {
		log.info("==testCreate==");
		String s_cont = "{\"bno\":148,\"reply\":\"댓글을 수정합니다.\",\"replyer\":\"user00\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/replies/40")
				.contentType(MediaType.APPLICATION_JSON)
				.content(s_cont))
				.andExpect(status().is(200));
	}
	
}
