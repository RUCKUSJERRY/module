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
						<input type="button" value="수정" onclick="location.href='ChatController?command=channelupdateform'">
						<input type="button" value="삭제" onclick="channeldelcon();">
					</td>
				</tr>
			</table>
			
		</div>
	</div>
	<div class="chat_container">
		<div id="chatinfo">
			채팅정보
		</div>
		<div id="chatarea">
			
		</div>
		<div id="texteditor">
			<input id="inputMessage" type="text" onkeydown="if(event.keyCode==13){send();}" />
			<input type="submit" value="전송" onclick="send();" />
		</div>
	</div>
	<div id="channel_add_insert" class="channel_add_insert">
		<form action="ChatController" method="get">
			<input type="hidden" name="command" value="channeladd">
			<input type="hidden" name="channelmaster" value="${loginDto.member_id }">
			<table border="1">
				<tr>
					<th>채널명</th>
					<td><input type="text" name="channelname"/></td>
				</tr>
				<tr>
					<th>채널정보</th>
					<td><input type="text" name="channelinfo"/></td>
				</tr>
				<tr>
					<th>공개 비공개 여부</th>
					<td>
						<select name="access">
							<option value="PUBLIC">공개</option>
							<option value="PRIVATE">비공개</option>
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
//채널추가 시

function addChannel() {	
	document.getElementById("channel_add_insert").style.display = "block";
	document.body.style.background = "gray";
	document.getElementById("channel_add_insert").style.background = "white";	
}

function addCancel() {
	document.getElementById("channel_add_insert").style.display = "none";
	document.body.style.background = "white";	
}

function channeldelcon() {
	
	var con = confirm("해당 채널을 삭제하시겠습니까?");
	var chnum = "";
	if (con) {
		location.href="ChatController?command=channeldelete&chnum="+chnum.value;
	} else {
		
	}
	
}

// 채팅영역

var webSocket = new WebSocket('ws://localhost:8787/semiPartOfLyj/websocket');

var inputMessage = document.getElementById('inputMessage');

var re_send = "";

webSocket.onerror = function(event) {
	onError(event)
};

webSocket.opopen = function(event) {
	onOpen(event)
};

webSocket.onmessage = function(event) {
	onMessage(event)
};

webSocket.onclose = function(event) {
	var div = document.createElement('div');
	div.innerHTML = "${loginDto.member_name } 님이 나가셨습니다.\n";
	
	onClose(event)
};

function onMessage(event) {
	
	var message = event.date.split("|\|");
	
	if (message[0] != re_send) {
		var who = document.createElement('div');
		who.style["float"]="right";
		who.style["display"]="inline-block";
		who.innerHTML = message[0];
		
		document.getElementById('chatarea').appendChild(who);
		var clear = document.createElement('div');
		clear.style["clear"]="both";
		document.getElementById('chatarea').appendChild(clear);
		
		re_send = message[0];
		
	}
	
	var div = document.createElement('div');
	div.style["float"]="right";
	div.style["display"]="inline-block";
	
	div.innerHTML = message[1];
	document.getElementById('chatarea').appendChild(div);
	
	var clear=document.createElement('div');
	clear.style["clear"]="both";
	document.getElementById('chatarea').appendChild(clear);
	
	chatarea.scrollTop = chatarea.scrollHeight;
	
}

function onOpen(event) {
	
	var div = document.createElement('div');
	div.style["float"]="left";
	
	div.innerHTML = " ${loginDto.member_name } 님 이 채팅방에 입장하였습니다.";
	document.getElementById('chatarea').appendChild(div);
	
	var clear=document.createElement('div');
	clear.style["clear"]="both";
	document.getElementById('chatarea').appendChild(clear);
	
	webSocket.send("${loginDto.member_name }님이 접속하였습니다.|\|메세지를 보내주세요.");
	
	
}

function onError(event) {
	console.log("서버 연결 에러" + event.data);
}

function send() {
	if (inputMessage.value != "") {
		
		if ("${loginDto.member_name }" != re_send) {
			var who = document.createElement('div');
			who.style["float"]="left";
			who.style["display"]="inline-block";
			who.innerHTML = "${loginDto.member_name }";
			
			document.getElementById('chatarea').appendChild(who);
			var clear = document.createElement('div');
			clear.style["clear"]="both";
			document.getElementById('chatarea').appendChild(clear);
			
			re_send = "${loginDto.member_name }";
			
		}
		
		webSocket.send("${loginDto.member_name }|\|" + inputMessage.value);
		
		var div=document.createElement('div');
		div.style["float"]="left";
		div.style["display"]="inline-block";
		
		div.innerHTML = inputMessage.value;
		document.getElementById('chatarea').appendChild(div);
		
		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		document.getElementById('chatarea').appendChild(clear);
		
		inputMessage.value = '';
		
		chatarea.scrollTop = chatarea.scrollHeight;
		
		re_send = "${loginDto.member_name }";
		
	}
	
}


</script>

</body>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
</html>