package com.spring.ex03;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

@WebServlet("/mem3.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		String action = req.getParameter("action");
		String nextPage = "";
		
		if(action == null || action.equals("listMembers")) {
			List<MemberVO> memberslist = dao.selectAllMemberList();
			
			req.setAttribute("membersList", memberslist);
			
			nextPage = "test02/listMembers.jsp";
			
		}else if(action.equals("selectMemberById")) {
			String id = req.getParameter("value");
			
			memberVO = dao.selectMemberById(id);
			
			req.setAttribute("member", memberVO);
			nextPage = "test02/memberInfo.jsp";
			
		}else if(action.equals("selectMemberByPwd")) {
			int pwd = Integer.valueOf(req.getParameter("value"));
			
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
					
			req.setAttribute("membersList", membersList);
			
			nextPage = "test02/listMembers.jsp";		
		}
		
		RequestDispatcher dispatch = req.getRequestDispatcher(nextPage);
		dispatch.forward(req, resp);
	}

}
