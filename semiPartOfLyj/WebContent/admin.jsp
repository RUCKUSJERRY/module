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
<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<!-- include summernote css/js -->
<link href="resources/summernote/summernote.css" rel="stylesheet">
<script src="resources/summernote/summernote.js"></script>
<style type="text/css">
	
	.chat_container {
		
		border: 1px solid black;
		position:absolute;
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

	<h1>Admin Page</h1>

	<input type="text" id="member_num" value="${loginDto.member_num }">
	<input type="text" id="member_id" value="${loginDto.member_id }">
	<input type="text" id="member_pw" value="${loginDto.member_pw }">
	<input type="text" id="member_name" value="${loginDto.member_name }">
	<div class="channel_container">
		
		<div class="channel_list">
			<table border="1">
				<tr>
					<td colspan="2"><a href="javascript:void(0)" onclick="callChatList(1);">전체채팅방</a></td>
				</tr>
	
	<% 
		List<RoomDto> list = (List<RoomDto>) request.getAttribute("channelAdminlist");
		
		if (list.size() == 0) {
	%>	
		<tr>
			<td colspan="2">채널이 없습니다. 채널을 추가해주세요.</td>
		</tr>
	<% 	
		} else {
			for (int i = 1; i < list.size(); i++) {
	%>	
			<tr>
				<td><a href="javascript:void(0)" onclick="callChatList(<%=list.get(i).getChannel_num()%>);"><%=list.get(i).getChannel_name() %></a></td>
				<td>
				<input type="button" value="관리" onclick="channeladmin(<%=list.get(i).getChannel_num() %>);" />
				<input type="button" value="삭제" onclick="channeldelcon(<%=list.get(i).getChannel_num() %>);" />
				</td>
			</tr>
	<% 		
			}
		}	
	%>	
				<tr>
					<td colspan="2" align="center">
					<input type="button" value="채널추가" onclick="addChannel();">
					</td>
				</tr>		
	
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
		
		
	<div id="channel_add_insert" class="channel_add_insert">
		<form action="ChatController" method="get">
			<input type="hidden" name="command" value="channeladd">
			<input type="hidden" name="channelmaster" value="${loginDto.member_id }">
			<table border="1">
				<tr>
					<th colspan="2">채널추가</th>
				</tr>
				<tr>
					<th>채널명</th>
					<td><input type="text" name="channelname"/></td>
				</tr>
				<tr>
					<th>채널정보</th>
					<td><input type="text" name="channelinfo"/></td>
				</tr>
				<tr>
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
<script type="text/javascript">
var channel_num = $("#channel_num").val();
var member_id = $("#member_id").val();
var member_name = $("#member_name").val();	


$(document).ready(function() {
	
	  $('#summernote').summernote({
		  height: 30,                 // set editor height
		  minHeight: 30,             // set minimum height of editor
		  maxHeight: 100,             // set maximum height of editor
		  focus: true,                 // set focus to editable area after initializing summernote
		  dialogsInBody: true,
		  lang: 'ko-KR',
		  toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold','underline']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['insert', ['link', 'picture', 'video']]
			  ],
		  	callbacks: {	//여기 부분이 이미지를 첨부하는 부분
		  		onImageUpload: function(files, editor, welEditable) {
				    sendFile(files[0], this);
				},
					onPaste: function (e) {
						var clipboardData = e.originalEvent.clipboardData;
						if (clipboardData && clipboardData.items && clipboardData.items.length) {
							var item = clipboardData.items[0];
							if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
								e.preventDefault();
							}
						}
					}
				}
	});
// 엔터 입력시 에어리어 초기화 및 값 전송	
	$('#summernote').on('summernote.enter', function() {		
				
				$(function() {
					var inputmsg = $('#summernote').val();
					var outmsg = inputmsg.replace(/<(\/?)p>/gi,'');
					inputmsg.replace('<br>','');
					$('#summernote').summernote('reset');
					send(outmsg);
				
				})
			
			
			
	});
			

});

/**
    * 이미지 파일 업로드
    */
    function sendFile(file, editor) {
        // 파일 전송을 위한 폼생성
 		data = new FormData();
 	    data.append("uploadFile", file);
 	    $.ajax({ // ajax를 통해 파일 업로드 처리
 	        data : data,
 	        type : "POST",
 	        url : "FileController",
 	        cache : false,
 	        contentType : false,
 	        processData : false,
 	        success : function(data) { // 처리가 성공할 경우
                // 에디터에 이미지 출력
                console.log(data.url);
                console.log(data.fileName);
 	        	$(editor).summernote('editor.insertImage', data.url+data.fileName);
 	        }
 	    });
 	}

$(function(){
	var id = $("#member_id").val();
	console.log(id);
	callChatList(1, id);
	
});

//채널추가 시
function addChannel() {	
	document.getElementById("channel_add_insert").style.display = "block";
	document.body.style.background = "gray";
	document.getElementById("channel_add_insert").style.background = "white";	
}
//채널추가 취소
function addCancel() {
	document.getElementById("channel_add_insert").style.display = "none";
	document.body.style.background = "white";
}
//채널삭제 확인
function channeldelcon(chnum) {	
	var con = confirm("해당 채널을 삭제하시겠습니까?");
	if (con) {
		location.href="ChatController?command=channeldelete&chnum="+chnum;
	}	
}
//채널 수정 시
function channeladmin(chnum) {
	location.href="ChatController?command=channelAdminform&channel_num="+chnum;
}
//채팅방 입장
function callChatList(chnum) {
	
	$('#chatarea').children().remove();
	
	var user = $("#member_id").val();
	$.ajax ({
		url:"ChatController?command=callChatList&channel_num="+chnum,
		dataType: "json",
		method: "get",
		success:function(data){
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				if (list[0][i].member_id == user) {
					
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						who.style["float"]="left";
						who.style["display"]="inline-block";
						who.append(list[0][i].member_id);
						
						document.getElementById('chatarea').appendChild(who);
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].member_id;
						
					}
					
					var div = document.createElement('div');
					div.style["float"]="left";
					div.style["background"]="yellow";
					div.style["display"]="inline-block";
					div.innerHTML = list[0][i].chat_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				} else {
					
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						who.style["float"]="right";
						who.style["display"]="inline-block";
						who.innerHTML = list[0][i].member_id;
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].member_id;
						
					}
					
					var div = document.createElement('div');
					div.style["float"]="right";
					div.style["background"]="white";
					div.style["display"]="inline-block";
					div.innerHTML = list[0][i].chat_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				}
				
			}
			
			var div = document.createElement('div');
			div.style["clear"]="both";
			div.style["background"]="skyblue";
			div.style["display"]="block";
			div.style["text-align"]="center";
			div.innerHTML = "--------------------이전 채팅--------------------";
			
			document.getElementById('chatarea').appendChild(div);
			
			chatarea.scrollTop = chatarea.scrollHeight;
			
		},
		error:function(){
			alert("통신 실패")
		}
		
		
	})
	
	channelInfo(chnum);
	
} 

function channelInfo(chnum) {
	
	$('#chatinfo').children().remove();
	
	$.ajax ({
		url:"ChatController?command=channelInfo&channel_num="+chnum,
		dataType: "text",
		method: "get",
		success:function(data){
			var res = data.split("|\\|");
			
			var channel_num = res[0];
			var channel_name = res[1];
			var channel_info = res[2];
			var channel_enabled = res[3];
			var channel_regdate = res[4];
			
			var input = document.createElement('input');
			input.id = "channel_num";
			input.type = "hidden";
			input.value = channel_num;
			document.getElementById('chatinfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "channel_enabled";
			input.type = "hidden";
			input.value = channel_enabled;
			document.getElementById('chatinfo').appendChild(input);
			
			var span = document.createElement('span');
			span.style["font-size"]="10px";
			span.style["display"]="inline-block";

			
			span.innerHTML = 
				" 채널이름 : "+ channel_name + "<br>" +
				" 채널정보 : "+ channel_info + "<br>" +
				" 채널생성일 : "+ channel_regdate;
			
			document.getElementById('chatinfo').appendChild(span);
			
			
		},
		error:function(){
			alert("통신 실패")
		}
	})
	
}

// 채팅영역

var webSocket = new WebSocket('ws://localhost:8787/semiPartOfLyj/websocket');

// var inputMessage = document.getElementById('summernote');

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
};

function onMessage(event) {
	
	var message = event.data.split("|\|");
	
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
	div.style["background"]="white";
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

function send(msg) {
	
	var chat_content = msg;
	console.log(chat_content);
		
	if (chat_content.value != "") {
		
		function  getParameterValues() {
			
			return "?command=chatinsert&channel_num=" + channel_num + "&member_id=" + member_id + "&member_name=" + member_name + "&chat_content=" + chat_content;
		}
		
		$.ajax({
			url:"ChatController"+getParameterValues(),
			dataType: "text",
			method: "get",
			success:function(msg){
				console.log("메세지 저장 완료")
			},
			error: function(){
				alert("통신 실패")
			}
			
			
		})	
	
		
		webSocket.send(member_id+"|\|" + chat_content);
		
		if (member_id != re_send) {
			var who = document.createElement('div');
			who.style["float"]="left";
			who.style["display"]="inline-block";
			who.innerHTML = "${loginDto.member_id }";
			
			document.getElementById('chatarea').appendChild(who);
			var clear = document.createElement('div');
			clear.style["clear"]="both";
			document.getElementById('chatarea').appendChild(clear);
			
			re_send = member_id;
			
		}
		
		var div=document.createElement('div');
		div.style["float"]="left";
		div.style["background"]="yellow";
		div.style["display"]="inline-block";
		
		div.innerHTML = chat_content;
		document.getElementById('chatarea').appendChild(div);
		
		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		document.getElementById('chatarea').appendChild(clear);
		
		chat_content.value = '';
		
		chatarea.scrollTop = chatarea.scrollHeight;
		
		re_send = member_id;
		
	}
	
}


</script>
</body>
</html>