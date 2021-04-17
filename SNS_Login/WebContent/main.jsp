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

네이버 로그인
<%
	String n_access_token = (String)request.getAttribute("N_access_token");
	String n_refresh_token = (String)request.getAttribute("N_refresh_token");
	String n_id = (String)request.getAttribute("N_id");
	String n_nickname = (String)request.getAttribute("N_nickname");
	String n_email = (String)request.getAttribute("N_email");
	
%>
네이버 access_token = <%=n_access_token %><br>
네이버 refresh_token = <%=n_refresh_token %><br>
네이버 id = <%=n_id %><br>
네이버 nickname = <%=n_nickname %><br>
네이버 email = <%=n_email %><br>

<hr>

구글 로그인
<%
	String g_access_token = (String)request.getAttribute("G_access_token");
	String g_refresh_token = (String)request.getAttribute("G_refresh_token");
	String g_id = (String)request.getAttribute("G_id");
	String g_email = (String)request.getAttribute("G_email");
	String g_picture = (String)request.getAttribute("G_picture");
%>

구글 access_token = <%=g_access_token %><br>
구글 refresh_token = <%=g_refresh_token %><br>
구글 id = <%=g_id %><br>
구글 email = <%=g_email %><br>
구글 picture = <%=g_picture %><br>
<img src="<%=g_picture%>">



</body>
</html>