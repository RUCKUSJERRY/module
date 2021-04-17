<%@page import="com.search.dto.SearchDto"%>
<%@page import="java.util.List"%>
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
	List<SearchDto> list = (List<SearchDto>)request.getAttribute("list");

%>

<h2>검색한 내용</h2>

<%
	if(list.size() == 0){
%>
	<h3>---- 검색한 내용이 없습니다. ----</h3>
<%		
	} else {
%>
	<table>
<%		
		for(SearchDto dto : list){
%>		
		<tr>
			<td><%=dto.getChat_content() %></td>
		</tr>
<%		
		}
	}
%>
	</table>


</body>
</html>