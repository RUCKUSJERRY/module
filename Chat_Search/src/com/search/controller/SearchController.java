package com.search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.search.biz.SearchBiz;
import com.search.dto.SearchDto;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public SearchController() {      
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//System.out.println("여기는 들어옴");
		
		String command = request.getParameter("command");
		//System.out.println(command);
		
		SearchBiz biz = new SearchBiz();
		
		if(command.equals("search")) {
			
			String content = request.getParameter("content");
			//System.out.println(content);
			
			List<SearchDto> list = biz.chatList(content);
			
			request.setAttribute("list", list);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("search.jsp");
			dispatch.forward(request, response);
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
