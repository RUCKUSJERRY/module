<%@page import="channel.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="channel.room.dto.RoomDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<% 
	RoomDto dto = (RoomDto) request.getAttribute("roomDto");
	List<MemberDto> list = (List<MemberDto>) request.getAttribute("channelMemberList");
%>

</head>
<body>

	<div id="channel_info_update">
		<form action="ChatController" method="get">
			<input type="hidden" name="command" value="channelUpdate">
			<input type="hidden" name="channel_num" value="<%=dto.getChannel_num() %>">
			<table border="1">
				<col width="200px;">
				<col width="500px;">
				<tr><th colspan="2" align="center">채널정보수정</th></tr>
				<tr>
					<th>채널명</th>
					<td><input type="text" name="channel_name" size="50" value="<%=dto.getChannel_name() %>"/></td>
				</tr>
				<tr>
					<th>채널정보</th>
					<td><input type="text" name="channel_info" size="50" value="<%=dto.getChannel_information() %>"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정"/>
						<input type="button" value="취소" onclick="history.back();"/>
					</td>
				</tr>
			</table>			
		</form>
	</div>
	<div class="channel_member_list">
		<div class="member_list">
		<table border="1">
			<tr>
				<th colspan="3">채널맴버리스트</th>
			</tr>
			<tr>
				<td>번호</td>
				<td>이름(아이디)</td>
				<td>Check</td>
			</tr>		
		<%
			if (list.size() == 0) {
		%>
			<tr>
				<th>맴버가 없습니다.</th>
			</tr>
		<%
		
			} else {
				for (int i = 0; i < list.size(); i++) {
		%>
				<tr>
					<th><%=i+1 %></th>
					<td align="center"><%=list.get(i).getMember_name() %>(<%=list.get(i).getMember_id() %>)</td>
					<td align="center"><input type="checkbox" name="chk" /></td>
				</tr>
		<%
				}
			}
		%>
		</table>
		</div>
		<div>
			<input type="button" value="맴버추가" onclick="addmember();" />
			<input type="button" value="맴버삭제" onclick="delmember();" />
		</div>	
			<form action="ChatController" method="get">
			<input type="hidden" name="command" value="channelinvite">
			<input type="hidden" name="channel_num" value="<%=dto.getChannel_num() %>">
		</form>
	</div>

</body>
<script type="text/javascript">
$(function(){
	
	var chname = <%=dto.getChannel_name() %>;
	
	$.ajax({
		url:"ChatController?command=callMemberList&channel_name="+chname,
		datetype:"text",
		method: "get",
		success:function(data){
			var list = data.split("|\\|");
	
		},
		error:function(){
			alert("채널맴버 조회 실패")
		}
	})	
		
	}
	
	
})

</script>
</html>