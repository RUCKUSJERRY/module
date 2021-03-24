<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

	<h1>Main Page</h1>
		
	<h2>num : ${loginDto.member_num }, id : ${loginDto.member_id }, pw : ${loginDto.member_pw }, name : ${loginDto.member_name }</h2>
	
	<div class="channel_container">
		
		<div class="channel_list">
			<table border="1">
				
				<tr>
					<td colspan="3" align="center"><input type="button" value="채널추가" onclick="addChannel();"></td>
				</tr>
				<tr>
					<td><a href="">전체채팅방</a></td>
					<td>
						<input type="button" value="수정" onclick="">
						<input type="button" value="삭제" onclick="">
					</td>
				</tr>
			</table>
			
		</div>
	</div>
	<div class="chat_container">
		<div class="chatarea">
			채팅영역
		</div>
		<div class="texteditor">
			<input id="inputMessage" type="text" onkeydown="if(event.keyCode==13){send();}" />
			<input type="submit" value="전송" onclick="send();" />
		</div>
	</div>
	<div id="channel_add_insert" class="channel_add_insert">
		<form action="ChatController" method="post">
			<input type="hidden" name="command" value="channeladd">
			<table border="1">
				<tr>
					<th>채널명</th>
					<td><input type="text" name="channelname"/></td>
				</tr>
				<tr>
					<th>채널정보</th>
					<td><input type="text" name="channelname"/></td>
				</tr>
				<tr>
					<th>공개 비공개 여부</th>
					<td>
						<select name="access">
							<option value="public">공개</option>
							<option value="private">비공개</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="채널생성"/>
						<input type="button" value="취소" onclick="addCancel();"/>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
	
</body>
</html>