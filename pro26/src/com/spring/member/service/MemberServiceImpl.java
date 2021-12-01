package com.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
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
