<%@page import="channel.room.dto.RoomDto"%>
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
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="resources/summernote/summernote.css" rel="stylesheet">
<script src="resources/summernote/summernote.js"></script>
<style type="text/css">
	
	.chat_container {
		
		border: 1px solid black;
		position:absolute;
		top: 5%;
		left: 30%;
		width: 60%;
		height: 80%;
		
		
	}
	
	#chatinfo {
		position: relative;
		border-bottom: 1px solid black;
		display: block;
		height: 15%;
	}
	
	#chatarea {
		position: relative;
		background: skyblue;
		height: 80%;
		overflow: auto;
		
	}
	
	

</style>
</head>

<body>

	<h1>Main Page</h1>

	<input type="text" id="member_num" value="${loginDto.member_num }">
	<input type="text" id="member_id" value="${loginDto.member_id }"> <br/>
	<input type="text" id="member_pw" value="${loginDto.member_pw }">
	<input type="text" id="member_name" value="${loginDto.member_name }">
	<input type="text" id="contextPath" value="${pageContext.request.contextPath}">
	<div class="channel_container">
		
		<div class="channel_list">
			<table border="1">
				<tr>
					<td colspan="2"><a href="javascript:void(0)" onclick="callChatList(1);">전체채팅방</a></td>
				</tr>
	
	<% 
		List<RoomDto> list = (List<RoomDto>) request.getAttribute("channellist");
		
		if (list.size() == 0) {
	%>	
		<tr>
			<td colspan="2">채널이 없습니다.</td>
		</tr>
	<% 	
	
		} else {
			for (int i = 0; i < list.size(); i++) {
	%>	
			<tr>
				<td><a href="javascript:void(0)" onclick="callChatList(<%=list.get(i).getChannel_num()%>);"><%=list.get(i).getChannel_name() %></a></td>
			</tr>
	<% 		
			}
		}	
	%>		
	
			</table>
			
		</div>
	</div>
	<div class="chat_container">
		<div id="chatinfo">
			
		</div>
		<div id="chatarea">
			
		</div>
			<textarea id="summernote" name="editordata" ></textarea>			
		</div>

<script type="text/javascript" src="resources/js/chat.js"></script>
</body>
</html>