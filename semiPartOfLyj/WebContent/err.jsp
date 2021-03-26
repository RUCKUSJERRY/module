<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	String err = request.getParameter("err");

%>
	<h1 style="color:red;">
		ERROR!!
		<%=err %>
	</h1>
	<a href="index.html">홈으로 돌아가기</a>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</html>