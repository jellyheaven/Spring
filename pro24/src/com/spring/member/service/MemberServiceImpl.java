package com.spring.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List listMembers() throws DataAccessException {
		
		List<MemberVO> memberslist = memberDAO.selectAllMemberList();
		return memberslist;
	}

	@Override
	public int addMember(MemberVO membeVO) throws DataAccessException {
		int result = memberDAO.insertMember(membeVO);
		return result;
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		int result = memberDAO.deleteMember(id);
		return result;
	}

}
