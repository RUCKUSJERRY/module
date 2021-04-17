<%@page import="com.statement.dto.StatementDto"%>
<%@page import="java.util.List"%>
<%@page import="com.statement.biz.StatementBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">

$(document).ready(function() { 
	var memnum = document.getElementById("memnum");
	var member_num = memnum.value;
	
	$.ajax({ 
		url: "StatementController?command=member_statement",
		type: "post",
		data: { 
				"member_num" : member_num,				
				"member_statement" : "1"
			  },
		success: function(data){
		},
		error: function(){ 
		}		
	});
});

$(window).bind("beforeunload", function (e){
	var memnum = document.getElementById("memnum");
	var member_num = memnum.value;
	 $.ajax({ 
		url: "StatementController?command=member_statement",
		type: "post",
		data: { 
				"member_num" : member_num,
				"member_statement" : "2"
			  },
		async: true,
		success: function(data){
		},
		error: function(){ 
		}		
	});
});

</script>
</head>
<body>
<input id="memnum" type="hidden" value="${loginDto.member_num }" >
<%
	StatementBiz biz = new StatementBiz();
	List<StatementDto> list = biz.allList();
	HttpSession session0 = request.getSession();
	StatementDto loginDto = (StatementDto) session0.getAttribute("loginDto");
%>
	<table>
<%	
	for(StatementDto dto : list){
		if(dto.getMember_num() != loginDto.getMember_num()) {
%>		
		<tr>
			<td><%=dto.getMember_id() %></td>
			<td><%=(dto.getMember_statement().equals("1"))?"온라인":"오프라인" %></td>
		</tr>		
<%				
		}
	}
%>
</table>


</body>
</html>