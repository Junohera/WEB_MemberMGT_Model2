package com.juno.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juno.dao.MemberDAO;
import com.juno.dto.MemberDTO;

public class Update_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberDTO member = new MemberDTO();
		member.setName(request.getParameter("name"));
		member.setUserid(request.getParameter("userid"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));

		if (MemberDAO.getIst().updateMember(member) == 1) {
			request.setAttribute("message", "update complete");
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member); // 세션 최신정보로 갱신
		} else {
			request.setAttribute("message", "update fail");
		}

		RequestDispatcher dp = request.getRequestDispatcher("main.jsp");
		dp.forward(request, response);
	}

}
