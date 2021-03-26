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
						<input type="button" value="채널삭제" onclick="delChannel(<%=dto.getChannel_num() %>);"/>
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
				<td><input type="checkbox" name="alldelcheck" onclick="delChkControl(this.checked);"/></td>
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
					<td align="center"><%=i+1 %></td>
					<td align="center"><%=list.get(i).getMember_name() %>(<%=list.get(i).getMember_id() %>)</td>
					<td align="center">
					<input type="checkbox" name="delchk" 
					value="member_name=<%=list.get(i).getMember_name() %>&member_id=<%=list.get(i).getMember_id() %>&channel_name=<%=dto.getChannel_name() %>"/>
					</td>
				</tr>
		<%
				}
			}
		%>
		<tr>
			<td colspan="3" align="right">
			<input type="button" value="초대하기" onclick="addMemberList();" />
			<input type="button" value="맴버삭제" onclick="delmember();" />
			</td>
		</tr>
		</table>
		</div>

		<div id="addMemberList">
		<table border="1">
			
		</table>
		</div>
		
			
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function delChkControl(bool) {
	
	var chk = document.getElementsByName("delchk");	
	for (var i = 0; i < chk.length; i++) {	
		chk[i].checked = bool;		
	}	
}

function addChkControl(bool) {
	
	var chk = document.getElementsByName("addchk");
	
	for (var i = 0; i < chk.length; i++) {
		
		chk[i].checked = bool;
		
	}
	
}

function delmember() {
	
	var chk = document.getElementsByName("delchk");
	for (var i = 0; i < chk.length; i++) {
		
		if (chk[i].checked) {
			$.ajax({
				url:"ChatController?command=delChannelMember&"+chk[i].value,
				method: "get",
				async: false,
				success:function(msg){
					console.log("채널 맴버 삭제 완료");
				},
				error:function(){
					console.log("채널 맴버 삭제 실패");
				}
			})			
		}		
	}	
}

function addMemberList() {
	
	var param = "<%=dto.getChannel_name() %>";
	
	$.ajax({
		url:"ChatController?command=addMemberList&channel_name="+param,
		dateType: "text",
		async: false,
		method: "post",
		success:function(data){
			var list = data.split("|br|");
			console.log(list);
			
			var table = document.createElement("table");
			table.border = "1";
			
			var tr = document.createElement("tr");
			var th = document.createElement("th");
			th.colSpan = "3";
			th.innerHTML = "초대가능맴버";
			tr.appendChild(th);
			table.appendChild(tr);
			
			var tr = document.createElement("tr");
			var td1 = document.createElement("td");
			td1.innerHTML = "번호";
			td1.setAttribute("align", "center")
			var td2 = document.createElement("td");
			td2.innerHTML = "이름(아이디)";
			td2.setAttribute("align", "center")
			var td3 = document.createElement("td");
			var input = document.createElement("input");
			input.type = "checkbox";
			input.name = "alladdchk";
			input.setAttribute("onclick", "addChkControl(this.checked)");
			td3.appendChild(input);
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			table.appendChild(tr);
			
			for (var i = 0; i < list.length; i++) {	
				
				var temp = list[i].split("|\\|");
				
				var tr = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var input = document.createElement("input");
				input.type = "checkbox";
				input.name = "addchk";
				input.value = "&member_name="+temp[0]+"&member_id="+temp[1];

				td1.innerHTML = i+1;
				td1.setAttribute("align", "center")
				td2.innerHTML = temp[0]+"("+temp[1]+")";
				td2.setAttribute("align", "center")
				td3.appendChild(input);
				
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				table.appendChild(tr);	

			}
			
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			var input1 = document.createElement("input");
			var input2 = document.createElement("input");
			input1.type = "button";
			input2.type = "button";
			input1.value = "맴버추가";
			input2.value = "취소";
			input1.setAttribute("onclick", "addChannelMember()");
			input2.setAttribute("onclick", "cancelAddMember()");
			td.colSpan = "3";
			td.setAttribute("align", "right");
			td.appendChild(input1);
			td.appendChild(input2);
			tr.appendChild(td);
			table.appendChild(tr);
			document.getElementById('addMemberList').appendChild(table);
			
			console.log("초대 맴버 호출 완료");
		},
		error:function(){
			console.log("채널 맴버 호출 실패");
		}
	})
	
}

function delChannel(chnum) {	
	var con = confirm("정말로 채널을 삭제하시겠습니까?")
	
	if(con) {
		location.href="ChatController?command=channelDelete&channel_num="+chnum;
	}
}

function cancelAddMember() {
	
	$('#addMemberList').children().remove();
	
}

function addChannelMember() {
	var param = "<%=dto.getChannel_name() %>";
	var addchk = document.getElementsByName("addchk");
	
	for (var i = 0; i < addchk.length; i++) {
		
		if (addchk[i].checked) {
			$.ajax({
				url:"ChatController?command=addChannelMember&channel_name="+param+addchk[i].value,
				method: "post",
				async: false,
				success:function(msg){
					console.log("채널 맴버 추가 완료");
				},
				error:function(msg) {
				console.log("채널 맴버 추가 실패");
				}
			})			
		}	
	
	
	}
}

</script>
</body>
</html>