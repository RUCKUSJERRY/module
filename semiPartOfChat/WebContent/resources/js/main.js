// 함수 실행을 위한 정보 변수 선언
var channel_seq = $("#channel_seq").val();
var member_id = $("#member_id").val();
var member_name = $("#member_name").val();
//채널삭제 확인
function channeldelcon(chnum) {	
	var con = confirm("해당 채널을 삭제하시겠습니까?");
	
	var member_id = $("#member_id").val();
	var channel_seq = chnum;
	function  getParameterValues() {
		
		return "?command=channelDelete&channel_seq=" + channel_seq + "&member_id=" + member_id;
	}
	
	if (con) {
		
		$.ajax({
			url:"ChannelController"+getParameterValues(),
			dataType: "text",
			method: "post",
			success:function(msg){
				alert(msg)
				location.reload();	
			},
			error: function(){
				alert("통신 실패")
			}	

		}
	)}
	
}
//채널 수정 시
function channelAdmin(chseq, chname, chinfo, chac) {
	var channel_seq = chseq;
	var channel_name = chname;
	var channel_information = chinfo;
	var channel_access = chac;	
	$("#update_channel_seq").val(channel_seq);
	$("#update_channel_name").val(channel_name);
	$("#update_channel_information").val(channel_information);
	$("#update_channel_access").val(channel_access).prop("selected",true);
}
// 페이지 최초 로딩시 전체채팅방 DB호출
$(function(){
	var chnum = $("#channel_seq_onload").val();
	callChatList(chnum);
});
// 채팅방 DB 호출 ajax
function callChatList(chnum) {
	$('#chatarea').children().remove();
	var user = $("#member_id").val();
	$.ajax ({
		url:"ChatController?command=callChatList&channel_seq="+chnum,
		dataType: "json",
		method: "post",
		success:function(data){
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				if (list[0][i].member_id == user) {
					
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						//who.style["float"]="left";
						who.style["display"]="inline-block";
						who.style["font-weight"]="bold";
						who.style["font-size"]="14px";
						who.style["padding-top"]="5px;"
						who.style["padding-bottom"]="2px;"
						who.style["padding-left"]="10px;"
						who.append(list[0][i].member_id);
						
						var icon = document.createElement('span');
						icon.setAttribute("class","glyphicon glyphicon-user");
						icon.setAttribute("aria-hidden","true");
						icon.style["color"]="gold";
						icon.style["width"]="15px;";
						icon.style["height"]="15px;";

						document.getElementById('chatarea').appendChild(icon);
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].member_id;
						
					}
					
					var div = document.createElement('div');
					//div.style["float"]="left";
					div.style["display"]="block";
					//div.style["font-weight"]="bold";
					div.style["color"]="#1D1C1D";
					div.style["padding-left"]="10px";
					div.style["padding-top"]="3px;"
					div.style["padding-bottom"]="3px;"
					div.innerHTML = list[0][i].chat_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				} else {
					
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						//who.style["float"]="right";
						who.style["display"]="inline-block";
						who.style["font-weight"]="bold";
						who.style["font-size"]="14px";
						who.style["padding-top"]="5px;"
						who.style["padding-bottom"]="2px;"
						who.style["padding-left"]="10px;"
						who.innerHTML = list[0][i].member_id;
						
						var icon = document.createElement('span');
						//icon.style["float"]="right";
						icon.setAttribute("class","glyphicon glyphicon-user");
						icon.setAttribute("aria-hidden","true");
						icon.style["color"]="gray";
						icon.style["width"]="15px;";
						icon.style["height"]="15px;";
						
						document.getElementById('chatarea').appendChild(icon);
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].member_id;
						
					}
					
					var div = document.createElement('div');
					//div.style["float"]="right";
					div.style["display"]="block";
					//div.style["font-weight"]="bold";
					div.style["color"]="#1D1C1D";
					div.style["padding-left"]="10px";
					div.style["padding-top"]="3px;"
					div.style["padding-bottom"]="3px;"
					div.innerHTML = list[0][i].chat_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				}
				
			}
			
			var div = document.createElement('div');
			div.style["clear"]="both";
			div.style["font-weight"]="bold";
			div.style["color"]="#1D1C1D";
			div.style["background"]="lightgray";
			div.style["display"]="block";
			div.style["text-align"]="center";
			div.innerHTML = "------------------------------이전 채팅은 여기까지 입니다.------------------------------";
			
			document.getElementById('chatarea').appendChild(div);
			
			chatarea.scrollTop = chatarea.scrollHeight;		
		},
		error:function(){
			alert("통신 실패")
		}		
	})
	$("#roominfo").children().remove();
	channelInfo(chnum);	
} 
// 채널정보 불러오는 ajax
function channelInfo(chnum) {
	
	$.ajax ({
		url:"ChannelController?command=channelSelect&channel_seq="+chnum,
		dataType: "text",
		method: "post",
		success:function(data){
			var res = data.split("|\\|");
			
			var channel_seq = res[0];
			var channel_info = res[1];
			var channel_enabled = res[2];
			var channel_regdate = res[3];
			
			var input = document.createElement('input');
			input.id = "channel_seq";
			input.type = "hidden";
			input.value = channel_seq;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "channel_enabled";
			input.type = "hidden";
			input.value = channel_enabled;
			document.getElementById('roominfo').appendChild(input);
			
			var span = document.createElement('span');
			span.style["font-size"]="10px";
			span.style["display"]="inline-block";

			span.innerHTML = 
				" 채널번호 : " + channel_seq + "<br>" +
				" 채널정보 : " + channel_info + "<br>" +
				" 채널생성일 : " + channel_regdate;
			
			document.getElementById('roominfo').appendChild(span);		
		},
		error:function(){
			alert("통신 실패")
		}
	})	
}
// 메세지방 DB 불러오는 ajax
function callMessageList(msgRoomNum) {
	$('#chatarea').children().remove();
	var user = $("#member_id").val();
	
	$.ajax ({
		url:"ChatController?command=callMessageList&messageroom_seq="+msgRoomNum,
		dataType: "json",
		method: "post",
		success:function(data){
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				if (list[0][i].to_id == user) {
					
					if (list[0][i].to_id != firstname) {
						var who = document.createElement('div');
						//who.style["float"]="left";
						who.style["display"]="inline-block";
						who.style["font-weight"]="bold";
						who.style["font-size"]="14px";
						who.style["padding-top"]="5px;"
						who.style["padding-bottom"]="2px;"
						who.style["padding-left"]="10px;"
						who.append(list[0][i].to_id);
						
						var icon = document.createElement('span');
						icon.setAttribute("class","glyphicon glyphicon-user");
						icon.setAttribute("aria-hidden","true");
						icon.style["color"]="gold";
						icon.style["width"]="15px;";
						icon.style["height"]="15px;";

						document.getElementById('chatarea').appendChild(icon);
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].to_id;
						
					}
					
					var div = document.createElement('div');
					//div.style["float"]="left";
					div.style["display"]="block";
					//div.style["font-weight"]="bold";
					div.style["color"]="#1D1C1D";
					div.style["padding-left"]="10px";
					div.style["padding-top"]="3px;"
					div.style["padding-bottom"]="3px;"
					div.innerHTML = list[0][i].message_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				} else {
					
					if (list[0][i].from_id != firstname) {
						var who = document.createElement('div');
						//who.style["float"]="right";
						who.style["display"]="inline-block";
						who.style["font-weight"]="bold";
						who.style["font-size"]="14px";
						who.style["padding-top"]="5px;"
						who.style["padding-bottom"]="2px;"
						who.style["padding-left"]="10px;"
						who.innerHTML = list[0][i].from_id;
						
						var icon = document.createElement('span');
						//icon.style["float"]="right";
						icon.setAttribute("class","glyphicon glyphicon-user");
						icon.setAttribute("aria-hidden","true");
						icon.style["color"]="gray";
						icon.style["width"]="15px;";
						icon.style["height"]="15px;";
						
						document.getElementById('chatarea').appendChild(icon);
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].from_id;
						
					}
					
					var div = document.createElement('div');
					//div.style["float"]="right";
					div.style["display"]="block";
					//div.style["font-weight"]="bold";
					div.style["color"]="#1D1C1D";
					div.style["padding-left"]="10px";
					div.style["padding-top"]="3px;"
					div.style["padding-bottom"]="3px;"
					div.innerHTML = list[0][i].message_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				}
				
			}
			
			var div = document.createElement('div');
			div.style["clear"]="both";
			div.style["font-weight"]="bold";
			div.style["color"]="#1D1C1D";
			div.style["background"]="lightgray";
			div.style["display"]="block";
			div.style["text-align"]="center";
			div.innerHTML = "------------------------------이전 메세지는 여기까지 입니다.------------------------------";
			
			document.getElementById('chatarea').appendChild(div);
			
			chatarea.scrollTop = chatarea.scrollHeight;		
		},
		error:function(){
			alert("통신 실패")
		}		
	})
	
	$("#roominfo").children().remove();
	
	messsageInfo(msgRoomNum);
	
}
// 메세지방 정보 불러오는 ajax
function messsageInfo (msgRoomNum) {
	console.log(msgRoomNum)
	$.ajax ({
		url:"ChatController?command=msgRoomSelect&messageroom_seq="+msgRoomNum,
		dataType: "text",
		method: "post",
		success:function(data){
			var res = data.split("|\\|");
			console.log(data);
			var messageroom_seq = res[0];
			var workspace_seq = res[1];
			var member_id = res[2];
			var member_name = res[3];
			var member2_id = res[4];
			var member2_name = res[5];
			var messsageroom_regdate = res[6];
			
			var input = document.createElement('input');
			input.id = "messageroom_seq";
			input.type = "hidden";
			input.value = messageroom_seq;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "messageInfo_member_id";
			input.type = "hidden";
			input.value = member_id;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "messageInfo_member_name";
			input.type = "hidden";
			input.value = member_name;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "messageInfo_member2_id";
			input.type = "hidden";
			input.value = member2_id;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "messageInfo_member2_name";
			input.type = "hidden";
			input.value = member2_name;
			document.getElementById('roominfo').appendChild(input);
			
			var span = document.createElement('span');
			span.style["font-size"]="10px";
			span.style["display"]="inline-block";

			span.innerHTML = 
				" 워크스페이스 : " + workspace_seq + "번의 " + 
				member_name + "(" + member_id + ")와 " + member2_name + "(" + member2_id + ")의 채팅방 <br>" +
				" 채널생성일 : " + messsageroom_regdate;
			
			document.getElementById('roominfo').appendChild(span);		
		},
		error:function(){
			alert("통신 실패")
		}
	})	
	
	
}

// 새 메세지 클릭시 맴버 목록 자동 조회 하는 ajax
function callMemberList() {
	// 해당 워크스페이스의 번호에 속해 있는 맴버들 - 나랑 대화중인 맴버 - 나
	var workspace_seq = $("#workspace_seq").val();
	var member_id = $("#member_id").val();
	var member_name = $("#member_name").val();
	
	function  getParameterValues() {
		
		return "?command=callMemberList&workspace_seq=" + workspace_seq + "&member_id=" + member_id + "&member_name=" + member_name;
	}
	
	$.ajax({
		
		url: "ChatController"+getParameterValues,
		dataType: "json",
		method: "post",
		
	})
	
}
// 채팅영역 Websocket
var webSocket = new WebSocket('ws://localhost:8787/semiPartOfChat/websocket');
var re_send = "";
webSocket.onerror = function(event) {
	onError(event)
};
webSocket.opopen = function() {
	onOpen()
};
webSocket.onmessage = function(event) {
	onMessage(event)
};
webSocket.onclose = function() {
	var div = document.createElement('div');
	div.innerHTML = member_name+"님이 나가셨습니다.";
};
function onMessage(event) {
	
	var message = event.data.split("|\|");
	
	if (message[0] != re_send) {
		var who = document.createElement('div');
		//who.style["float"]="right";
		who.style["display"]="inline-block";
		who.style["font-weight"]="bold";
		who.style["font-size"]="14px";
		who.style["padding-top"]="5px;"
		who.style["padding-bottom"]="2px;"
		who.style["padding-left"]="10px;"
		who.innerHTML = message[0];
		
		var icon = document.createElement('span');
		//icon.style["float"]="right";
		icon.setAttribute("class","glyphicon glyphicon-user");
		icon.setAttribute("aria-hidden","true");
		icon.style["color"]="gray";
		icon.style["width"]="15px;";
		icon.style["height"]="15px;";
		
		document.getElementById('chatarea').appendChild(icon);
		document.getElementById('chatarea').appendChild(who);
		
		var clear = document.createElement('div');
		clear.style["clear"]="both";
		document.getElementById('chatarea').appendChild(clear);
		
		re_send = message[0];
		
	}
	
	var div = document.createElement('div');
	//div.style["float"]="right";
	div.style["display"]="block";
	//div.style["font-weight"]="bold";
	div.style["color"]="#1D1C1D";
	div.style["padding-left"]="10px";
	div.style["padding-top"]="3px;"
	div.style["padding-bottom"]="3px;"
	
	div.innerHTML = message[1];
	document.getElementById('chatarea').appendChild(div);
	
	var clear=document.createElement('div');
	clear.style["clear"]="both";
	document.getElementById('chatarea').appendChild(clear);
	
	chatarea.scrollTop = chatarea.scrollHeight;

	
}
function onOpen() {
	
	var div = document.createElement('div');
	div.style["float"]="left";
	
	div.innerHTML = member_name + "님 이 채팅방에 입장하였습니다.";
	document.getElementById('chatarea').appendChild(div);
	
	var clear=document.createElement('div');
	clear.style["clear"]="both";
	document.getElementById('chatarea').appendChild(clear);
	
	webSocket.send(member_name+"님이 채팅방에 입장하였습니다.|\|메세지를 보내주세요.");
	
}
function onError(event) {
	console.log("서버 연결 에러" + event.data);
}
function send(msg) {
	// 채팅 메세지가 널이 아니면~
	var content = msg;
	if (content.value != "") {
		// 맴버 2아이디가 널이 아니면~ 메세지인서트
		var member2_id = $("#messageInfo_member2_id").val();
		console.log(member2_id);
		
		if (member2_id != null) {
			
			var messageroom_seq = $("#messageroom_seq").val();
			var to_id = $("#messageInfo_member_id").val();
			var to_name = $("#messageInfo_member_name").val();
			var from_id = $("#messageInfo_member2_id").val();
			var from_name = $("#messageInfo_member2_name").val();
			var message_content = msg;
			
			function  getParameterValues() {
				
				return "?command=messageInsert&messageroom_seq=" + messageroom_seq
				+ "&to_id=" + to_id
				+ "&to_name=" + to_name
				+ "&from_id=" + from_id
				+ "&from_name=" + from_name
				+ "&message_content="+ message_content;
			}
						
			$.ajax({
				url:"ChatController"+getParameterValues(),
				dataType: "text",
				method: "post",
				success:function(){
					console.log("메세지 저장 완료")
				},
				error: function(){
					alert("통신 실패")
				}	
			})

			webSocket.send(to_id+"|\|" + message_content);
			
			if (to_id != re_send) {
				var who = document.createElement('div');
				//who.style["float"]="left";
				who.style["display"]="inline-block";
				who.style["font-weight"]="bold";
				who.style["font-size"]="14px";
				who.style["padding-top"]="5px;"
				who.style["padding-bottom"]="2px;"
				who.style["padding-left"]="10px;"	
				who.innerHTML = to_id;
				
				var icon = document.createElement('span');
				icon.style["color"]="gold";
				icon.style["width"]="15px;";
				icon.style["height"]="15px;";
				icon.setAttribute("class","glyphicon glyphicon-user");
				icon.setAttribute("aria-hidden","true");
				
				document.getElementById('chatarea').appendChild(icon);
				document.getElementById('chatarea').appendChild(who);
				var clear = document.createElement('div');
				clear.style["clear"]="both";
				document.getElementById('chatarea').appendChild(clear);
				
				re_send = to_id;
				
			}
			
			var div=document.createElement('div');
			//div.style["float"]="left";
			div.style["display"]="block";
			//div.style["font-weight"]="bold";
			div.style["color"]="#1D1C1D";
			div.style["padding-left"]="10px";
			div.style["padding-top"]="3px;"
			div.style["padding-bottom"]="3px;"
			
			div.innerHTML = message_content;
			document.getElementById('chatarea').appendChild(div);
			
			var clear = document.createElement('div');
			clear.style["clear"] = "both";
			document.getElementById('chatarea').appendChild(clear);
			
			message_content.value = '';
			
			chatarea.scrollTop = chatarea.scrollHeight;
			
			re_send = to_id;
			
		  // 그게 아니면 채팅 인서트
		} else {
			
			var channel_seq = $("#channel_seq").val();
			var member_id = $("#member_id").val();
			var member_name = $("#member_name").val();
			var chat_content = msg;
			console.log(chat_content);
			
			function  getParameterValues() {
				
				return "?command=chatInsert&channel_seq=" + channel_seq + "&member_id=" + member_id + "&member_name=" + member_name + "&chat_content=" + chat_content;
			}
			
			$.ajax({
				url:"ChatController"+getParameterValues(),
				dataType: "text",
				method: "post",
				success:function(){
					console.log("채팅 저장 완료")
				},
				error: function(){
					alert("통신 실패")
				}	
			})
			
			webSocket.send(member_id+"|\|" + chat_content);
			
			if (member_id != re_send) {
				var who = document.createElement('div');
				//who.style["float"]="left";
				who.style["display"]="inline-block";
				who.style["font-weight"]="bold";
				who.style["font-size"]="14px";
				who.style["padding-top"]="5px;"
				who.style["padding-bottom"]="2px;"
				who.style["padding-left"]="10px;"	
				who.innerHTML = member_id;
				
				var icon = document.createElement('span');
				icon.style["color"]="gold";
				icon.style["width"]="15px;";
				icon.style["height"]="15px;";
				icon.setAttribute("class","glyphicon glyphicon-user");
				icon.setAttribute("aria-hidden","true");
				
				document.getElementById('chatarea').appendChild(icon);
				document.getElementById('chatarea').appendChild(who);
				var clear = document.createElement('div');
				clear.style["clear"]="both";
				document.getElementById('chatarea').appendChild(clear);
				
				re_send = member_id;
				
			}
			
			var div=document.createElement('div');
			//div.style["float"]="left";
			div.style["display"]="block";
			//div.style["font-weight"]="bold";
			div.style["color"]="#1D1C1D";
			div.style["padding-left"]="10px";
			div.style["padding-top"]="3px;"
			div.style["padding-bottom"]="3px;"
			
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
	
}
// 썸머노트 실행
$(document).ready(function() {
	
	  $('#summernote').summernote({
		  height: 100,                 // set editor height
		  minHeight: 30,             // set minimum height of editor
		  maxHeight: 100,             // set maximum height of editor
		  focus: true,                 // set focus to editable area after initializing summernote
		  dialogsInBody: true,
		  placeholder: '메세지를 입력해주세요.',
		  lang: 'ko-KR',
		  toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold','underline']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['insert', ['link', 'picture', 'video']]
			  ],
		  	callbacks: {	//여기 부분이 이미지를 첨부하는 부분
		  		onImageUpload: function(files) {
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
	  
	$('.note-statusbar').hide();
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
 		var data = new FormData();
 	    data.append("uploadFile", file);
 	    $.ajax({ // ajax를 통해 파일 업로드 처리
 	        data : data,
 	        type : "POST",
 	        url : "FileController",
 	        cache : false,
 	        async : false,
 	        contentType : false,
 	        processData : false,
 	        success : function(data) { // 처리가 성공할 경우
                // 에디터에 이미지 출력

 	        	$(editor).summernote('editor.insertImage', data.url);
 	        }
 	    });
 	}
//워크스페이스 삭제 확인 함수    
function workspaceDelcon(wsseq) {
    	
    	var con = confirm("정말로 채널을 삭제하시겠습니까?");
    	var workspace_seq = wsseq;
    	
    	if (con) {
    		location.href='ChannelController?command=WorkSpaceDel&workspace_seq='+workspace_seq;
    	}
}

// 워크스페이스 관리 클릭시 해당 워크스페이스의 맴버 목록 호출
function callWorkspaceMemberList() {
	
	var workspace_seq = $("#workspace_seq").val();
	var member_id = $("#member_id").val();
	
	function  getParameterValues() {
		
		return "?command=callWorkspaceMemberList&workspace_seq=" + workspace_seq + "&member_id=" + member_id;
	}
	
	$.ajax({
		url:"ChannelController"+getParameterValues(),
		dateType:"json",
		method:"post",
		success:function(data){
			var list = data.result;

			for (var i = 0; i < list[0].length; i++) {
				
			}
		},
		error: function(){
			alert("통신 실패")
		}	
		
	})
	
	
}

// 워크스페이스 관리에서 초대하기 누르면 밑에 초대맴버 목록 노출
function callWorkspaceInviteList() {

	var member_id = $("#member_id").val();
	var workspace_seq = $("#workspace_seq").val();
	
	function  getParameterValues() {
		
		return "?command=callWorkspaceInviteList&member_id=" + member_id + "&workspace_seq=" + workspace_seq;
	}
	
	$.ajax({
		url:"ChannelController"+getParameterValues(),
		dateType:"json",
		method:"post",
		success:function(data){
			var list = data.result;
			console.log(data);
			for (var i = 0; i < list[0].length; i++) {
				var member_num = list[0].member_num;
				var member_id = list[0].member_id;
				var member_name = list[0].member_name;
				
				
				
				
				
			}
		},
		error: function(){
			alert("통신 실패")
		}	
		
	})
	
}
