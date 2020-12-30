package com.juno.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juno.dao.MemberDAO;

public class SelectAll_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("memberList", MemberDAO.getIst().selectAll());
		
		RequestDispatcher dp = request.getRequestDispatcher("member/selectAll.jsp");
		dp.forward(request, response);
	}

}
