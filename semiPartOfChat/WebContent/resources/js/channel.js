$(function(){
	
	//썸머노트 실행
	$(document)
			.ready(
					function() {
						$('#summernote')
								.summernote(
										{
											height : 100, // set editor height
											minHeight : 30, // set minimum height of
											// editor
											maxHeight : 100, // set maximum
											// height of editor
											focus : true, // set focus to editable
											// area after
											// initializing
											// summernote
											dialogsInBody : true,
											placeholder : '메세지를 입력해주세요.',
											lang : 'ko-KR',
											toolbar : [
													// [groupName, [list of button]]
													[ 'style',
															[ 'bold', 'underline' ] ],
													[
															'para',
															[ 'ul', 'ol',
																	'paragraph' ] ],
													[
															'insert',
															[ 'link', 'picture',
																	'video' ] ] ],
											callbacks : { // 여기 부분이 이미지를 첨부하는 부분
												onImageUpload : function(files) {
													sendFile(files[0], this);
												},
												onPaste : function(e) {
													var clipboardData = e.originalEvent.clipboardData;
													if (clipboardData
															&& clipboardData.items
															&& clipboardData.items.length) {
														var item = clipboardData.items[0];
														if (item.kind === 'file'
																&& item.type
																		.indexOf('image/') !== -1) {
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
							var outmsg = inputmsg.replace(/<(\/?)p>/gi, '');
							inputmsg.replace('<br>', '');
							$('#summernote').summernote('reset');
								send(outmsg);
							})
						});
		});
	
	var member_num = $("#member_num").val();
	var workspace_num = $("#workspace_num").val();
	
	// 채널 생성 모달 안에 맴버넘버, 워크스페이스넘버 히든 인풋으로 넣어주기
	var res = document.querySelector('#channelCommand');
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_num");
	input.value = member_num;
	res.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","workspace_num");
	input.value = workspace_num;
	res.appendChild(input);
	
	//채널 리스트 호출해주는 함수 실행
	if (member_num != null) {
		function  getParameterValues() {
			return "?command=selectMemberChannel&member_num=" + member_num +"&workspace_num=" + workspace_num;
		}
		
		$.ajax({
			url:"ChannelController"+getParameterValues(),
			dataType: "json",
			method: "post",
			success:function(data){
				
				var list = data.result;
				if (list != null) {
					
					for (var i = 0; i < list[0].length; i++) {
						
						var li = document.createElement('li');
						li.setAttribute("class","list-group-item");
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default");
						button.setAttribute("onclick","selectChatList("+list[0][i].channel_num+","+member_num+");");
						button.innerHTML = list[0][i].channel_name;
						li.appendChild(button);

						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("data-toggle","modal");
						button.setAttribute("data-target","#adminChannelForm");
						button.setAttribute("onclick",
						"channelAdmin("
						+list[0][i].channel_num+","
						+member_num+","
						+"'"+list[0][i].channel_name+"'"+","
						+"'"+list[0][i].channel_information+"'"+","
						+"'"+list[0][i].channel_access+"'"+");");
						button.innerHTML = "관리";
						li.appendChild(button);
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("onclick","channelDelcon("+list[0][i].channel_num+","+member_num+")");
						button.innerHTML = "삭제";
						li.appendChild(button);

						$("#ChannelArea").append(li);
						
					}
				} else {
					
					var p = document.createElement('p');
					p.innerHTML = "워크스페이스가 없습니다. 새로 워크스페이스를 생성해주세요.";
					$("#ChannelArea").append(p);
					
				}
					
			},
			error: function(){
				alert("채널 불러오기 실패")
			}		
		})		
	} else {
		alert("로그인 세션이 만료되었습니다. 다시 로그인해주세요.")
		location.href="member_login.jsp";
	}

})

//이미지 파일 업로드
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
//채널 수정 시
function channelAdmin(chnum, memnum, chname, chinfo, chac) {
	
	var channel_num = chnum;
	var member_num = memnum;
	var channel_name = chname;
	var channel_information = chinfo;
	var channel_access = chac;
	$("#update_channel_num").val(channel_num);
	$("#update_member_num").val(member_num);
	$("#update_channel_name").val(channel_name);
	$("#update_channel_information").val(channel_information);
	$("#update_channel_access").val(channel_access).prop("selected", true);
}
// 채널삭제 확인
function channelDelcon(chnum, memnum) {
	var con = confirm("해당 채널을 삭제하시겠습니까?");

	if (con) {
		
		var channel_num = chnum;
		var member_num = memnum;
		var workspace_num = $("#workspace_num").val();

		function getParameterValues() {

			return "?command=deleteChannel&channel_num=" + channel_num
					+ "&member_num=" + member_num;
		}

		$.ajax({
			url : "ChannelController" + getParameterValues(),
			dataType : "text",
			method : "post",
			success : function(msg) {
				alert(msg)
				location.href = 'ChannelController?command=channelIn&member_num='+member_num+"&workspace_num="+workspace_num;
			},
			error : function() {
				alert("통신 실패")
			}

		})
	}

}













//채팅방 DB 호출 ajax
function selectChatList(chnum, memnum) {
	$('#chatarea').children().remove();
	
	var member_num = memnum;
	var channel_num = chnum;
	
	function  getParameterValues() {
		return "?command=selectChatList&channel_num=" + channel_num;
	}
	
	$.ajax({
		url : "ChatController"+getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				if (list[0][i].member_num == member_num) {
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						// who.style["float"]="left";
						who.style["display"] = "inline-block";
						who.style["font-weight"] = "bold";
						who.style["font-size"] = "14px";
						who.style["padding-top"] = "5px;"
						who.style["padding-bottom"] = "2px;"
						who.style["padding-left"] = "10px;"
						who.append(list[0][i].member_id);
							var icon = document.createElement('span');
								icon.setAttribute("class",
										"glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gold";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";

								document.getElementById('chatarea')
										.appendChild(icon);
								document.getElementById('chatarea')
										.appendChild(who);

								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								document.getElementById('chatarea')
										.appendChild(clear);

								firstname = list[0][i].member_id;

							}

							var div = document.createElement('div');
							// div.style["float"]="left";
							div.style["display"] = "block";
							// div.style["font-weight"]="bold";
							div.style["color"] = "#1D1C1D";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].chat_content;
							document.getElementById('chatarea')
									.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							document.getElementById('chatarea').appendChild(clear);

						} else {

							if (list[0][i].member_id != firstname) {
								var who = document.createElement('div');
								// who.style["float"]="right";
								who.style["display"] = "inline-block";
								who.style["font-weight"] = "bold";
								who.style["font-size"] = "14px";
								who.style["padding-top"] = "5px;"
								who.style["padding-bottom"] = "2px;"
								who.style["padding-left"] = "10px;"
								who.innerHTML = list[0][i].member_id;

								var icon = document.createElement('span');
								// icon.style["float"]="right";
								icon.setAttribute("class",
										"glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gray";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";

								document.getElementById('chatarea')
										.appendChild(icon);
								document.getElementById('chatarea')
										.appendChild(who);

								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								document.getElementById('chatarea')
										.appendChild(clear);

								firstname = list[0][i].member_id;

							}

							var div = document.createElement('div');
							// div.style["float"]="right";
							div.style["display"] = "block";
							// div.style["font-weight"]="bold";
							div.style["color"] = "#1D1C1D";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].chat_content;
							document.getElementById('chatarea')
									.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							document.getElementById('chatarea').appendChild(clear);

						}

					}

					var div = document.createElement('div');
					div.style["clear"] = "both";
					div.style["font-weight"] = "bold";
					div.style["color"] = "#1D1C1D";
					div.style["background"] = "lightgray";
					div.style["display"] = "block";
					div.style["text-align"] = "center";
					div.innerHTML = "------------------------------이전 채팅은 여기까지 입니다.------------------------------";

					document.getElementById('chatarea').appendChild(div);

					chatarea.scrollTop = chatarea.scrollHeight;
				},
				error : function() {
					alert("통신 실패")
				}
			})
	$("#roominfo").children().remove();
	channelInfo(chnum);
}
// 채널정보 불러오는 ajax
function channelInfo(chnum) {

	$.ajax({
		url : "ChannelController?command=selectOneChannel&channel_num=" + chnum,
		dataType : "text",
		method : "post",
		success : function(data) {
			var res = data.split("|\\|");

			var channel_num = res[0];
			var workspace_num = res[1];
			var member_num = res[2];
			var channel_name = res[3];
			var channel_info = res[4];
			var channel_access = res[5];
			var channel_enabled = res[6];
			var channel_regdate = res[7];
			
			var input = document.createElement('input');
			input.id = "channel_num";
			input.type = "hidden";
			input.value = channel_num;
			document.getElementById('roominfo').appendChild(input);

			var input = document.createElement('input');
			input.id = "channel_enabled";
			input.type = "hidden";
			input.value = channel_enabled;
			document.getElementById('roominfo').appendChild(input);

			var span = document.createElement('span');
			span.style["font-size"] = "10px";
			span.style["display"] = "inline-block";

			span.innerHTML = 
			" 채널번호 : " + channel_num + " 워크스페이스 번호 : " + workspace_num + "<br>" + " 맴버 번호 : " + member_num + "<br>" + 
			" 채널이름 : " + channel_name + " 채널정보 : " + channel_info + "<br>" + 
			" 공개/비공개 여부 : " + channel_access + " 채널 사용가능 여부 : " + channel_enabled + "<br>" + 
			" 채널생성일 : " + channel_regdate;

			document.getElementById('roominfo').appendChild(span);
		},
		error : function() {
			alert("통신 실패")
		}
	})
}
//채팅영역 Websocket 접속 함수들
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
	div.innerHTML = member_name + "님이 나가셨습니다.";
};
// 웹소캣으로 send한 메세지를 출력시켜주는 함수
function onMessage(event) {

	var message = event.data.split("|\|");

	if (message[0] != re_send) {
		var who = document.createElement('div');
		// who.style["float"]="right";
		who.style["display"] = "inline-block";
		who.style["font-weight"] = "bold";
		who.style["font-size"] = "14px";
		who.style["padding-top"] = "5px;"
		who.style["padding-bottom"] = "2px;"
		who.style["padding-left"] = "10px;"
		who.innerHTML = message[0];

		var icon = document.createElement('span');
		// icon.style["float"]="right";
		icon.setAttribute("class", "glyphicon glyphicon-user");
		icon.setAttribute("aria-hidden", "true");
		icon.style["color"] = "gray";
		icon.style["width"] = "15px;";
		icon.style["height"] = "15px;";

		document.getElementById('chatarea').appendChild(icon);
		document.getElementById('chatarea').appendChild(who);

		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		document.getElementById('chatarea').appendChild(clear);

		re_send = message[0];

	}

	var div = document.createElement('div');
	// div.style["float"]="right";
	div.style["display"] = "block";
	// div.style["font-weight"]="bold";
	div.style["color"] = "#1D1C1D";
	div.style["padding-left"] = "10px";
	div.style["padding-top"] = "3px;"
	div.style["padding-bottom"] = "3px;"

	div.innerHTML = message[1];
	document.getElementById('chatarea').appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	document.getElementById('chatarea').appendChild(clear);

	chatarea.scrollTop = chatarea.scrollHeight;

}
// 웹소켓에 접속시 실행되는 함수
function onOpen() {

	var div = document.createElement('div');
	div.style["float"] = "left";

	div.innerHTML = member_name + "님 이 채팅방에 입장하였습니다.";
	document.getElementById('chatarea').appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	document.getElementById('chatarea').appendChild(clear);

	webSocket.send(member_name + "님이 채팅방에 입장하였습니다.|\|메세지를 보내주세요.");

}
// 웹소켓 에러시 실행되는 함수
function onError(event) {

	console.log("서버 연결 에러" + event.data);
}
// 웹소켓에 메세지 입력시 보내주는 함수
function send(msg) {
	// 채팅 메세지가 널이 아니면~
	var content = msg;
	if (content.value != "") {

			var channel_num = $("#channel_num").val();
			var member_num = $("#member_num").val();
			var member_id = $("#member_id").val();
			var member_name = $("#member_name").val();
			var chat_content = msg;
			console.log(chat_content);

			function getParameterValues() {

				return "?command=insertChat&channel_num=" + channel_num
						+ "&member_num=" + member_num 
						+ "&member_id=" + member_id
						+ "&member_name=" + member_name
						+ "&chat_content=" + chat_content;
			}

			$.ajax({
				url : "ChatController" + getParameterValues(),
				dataType : "text",
				method : "post",
				success : function() {
					console.log("채팅 저장 완료")
				},
				error : function() {
					alert("통신 실패")
				}
			})

			webSocket.send(member_id + "|\|" + chat_content);

			if (member_id != re_send) {
				var who = document.createElement('div');
				// who.style["float"]="left";
				who.style["display"] = "inline-block";
				who.style["font-weight"] = "bold";
				who.style["font-size"] = "14px";
				who.style["padding-top"] = "5px;"
				who.style["padding-bottom"] = "2px;"
				who.style["padding-left"] = "10px;"
				who.innerHTML = member_id;

				var icon = document.createElement('span');
				icon.style["color"] = "gold";
				icon.style["width"] = "15px;";
				icon.style["height"] = "15px;";
				icon.setAttribute("class", "glyphicon glyphicon-user");
				icon.setAttribute("aria-hidden", "true");

				document.getElementById('chatarea').appendChild(icon);
				document.getElementById('chatarea').appendChild(who);
				var clear = document.createElement('div');
				clear.style["clear"] = "both";
				document.getElementById('chatarea').appendChild(clear);

				re_send = member_id;

			}

			var div = document.createElement('div');
			// div.style["float"]="left";
			div.style["display"] = "block";
			// div.style["font-weight"]="bold";
			div.style["color"] = "#1D1C1D";
			div.style["padding-left"] = "10px";
			div.style["padding-top"] = "3px;"
			div.style["padding-bottom"] = "3px;"

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



