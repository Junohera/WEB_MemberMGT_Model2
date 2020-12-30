package com.juno.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juno.dao.MemberDAO;

public class Delete_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String url = "member/loginForm.jsp";
		if (session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "session expired, Please try again after login");
		} else {
			if (MemberDAO.getIst().deleteMember(request.getParameter("userid")) == 1) {
				request.setAttribute("message", "delete complete");
				session.invalidate();
			} else {
				request.setAttribute("message", "delete fail");
			}
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
