<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<%
String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String type = request.getParameter("type");
String totalPrice = request.getParameter("totalPrice");


System.out.println("name: " + name);
System.out.println("email: " + email);
System.out.println("phone: " + phone);
System.out.println("type: " + type);
System.out.println("totalPrice: " + totalPrice);
System.out.println("checkout에서 res로 받아온값 완료");
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>결제확인</h1>

	<table border="1">
		<tr>
			<th>결제자이름</th>
			<th>결제이메일</th>
			<th>결제연락처</th>
			<th>결제금액</th>
			<th>결제일</th>
		</tr>
		<tr>
			<td><%=name %></td>
			<td><%=email %></td>
			<td><%=phone %></td>
			<td><%=type %></td>
			<td><%=totalPrice %></td>
		</tr>


		<tr>
			<td colspan="5" align="right">
			<input type="button" value="메인으로" onclick="location.href='index.html'" />
			</td>
		</tr>

	</table>


</body>
</html>