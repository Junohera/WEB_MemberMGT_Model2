package com.juno.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juno.dao.MemberDAO;
import com.juno.dto.MemberDTO;

public class Login_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp";

		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		MemberDTO member = MemberDAO.getIst().findMember(userid);
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginUser") != null) {
			url = "main.jsp";
		} else {
			if (member == null) request.setAttribute("message", "id not found");
			else {
				if (member.getPwd() != null) {
					if (!member.getPwd().equals(pwd)) request.setAttribute("message", "password diff");
					else {
						session.setAttribute("loginUser", member);
						url = "main.jsp";
					}
				} else request.setAttribute("message", "invalid user info. call to admin");
			}
		}

		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}
}
