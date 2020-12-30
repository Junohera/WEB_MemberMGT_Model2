package com.juno.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juno.dao.MemberDAO;

public class Update_Form_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/updateForm.jsp";
		String userid = request.getParameter("userid");
		request.setAttribute("member", MemberDAO.getIst().findMember(userid));
		
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "session expired. please login again");
			url = "member/loginForm.jsp";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
