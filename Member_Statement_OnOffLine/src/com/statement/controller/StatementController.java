package com.statement.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.statement.biz.StatementBiz;
import com.statement.dto.StatementDto;

@WebServlet("/StatementController")
public class StatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public StatementController() {
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		StatementBiz biz = new StatementBiz();
		
		if(command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			StatementDto dto = biz.login(id, biz.getSHA256(pw));
			HttpSession session = request.getSession();

			if (dto != null) {
				session.setAttribute("loginDto", dto);
				response.sendRedirect("statement.jsp");	
			} else {
				response.sendRedirect("index.jsp");
			}
			
		} else if (command.equals("member_statement")) {
			String member_num = request.getParameter("member_num");
			String member_statement = request.getParameter("member_statement");
			System.out.println(member_num);
			System.out.println(member_statement);
			
			StatementDto dto = new StatementDto();
			dto.setMember_num(Integer.parseInt(member_num));
			dto.setMember_statement(member_statement);
			
			int res = biz.updateStatement(dto);
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
