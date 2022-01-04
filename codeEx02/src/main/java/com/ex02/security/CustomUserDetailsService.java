package com.ex02.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ex02.domain.CustomUser;
import com.ex02.domain.MemberVO;
import com.ex02.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Setter(onMethod_ = {@Autowired})
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("Load User By UserName : "+username);
		
		MemberVO vo = memberMapper.read(username);
		
		log.warn("queried by member mapper:" + vo);
		
		return vo == null ? null : new CustomUser(vo);
	}

}
