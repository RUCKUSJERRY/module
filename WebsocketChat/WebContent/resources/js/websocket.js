//채팅영역 Websocket 접속 함수들
var webSocket = new WebSocket('ws://localhost:8787/WebsocketChat/websocket');
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
	var member_name = $('#member_name').val();

	var query = document.querySelector('#chatarea');
	
	var div = document.createElement('div');
	div.style["float"] = "left";

	div.innerHTML = member_name + "님 이 채팅방에서 퇴장하였습니다.";
	query.appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	query.appendChild(clear);

};
// 웹소캣으로 send한 메세지를 출력시켜주는 함수
function onMessage(event) {

	var today = new Date();
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate();  // 날짜
	
	var hours = today.getHours(); // 시
	var minutes = today.getMinutes();  // 분
	var seconds = today.getSeconds();  // 초
	
	var realTime = month + "월 " + date +"일 "+ hours + ":" + minutes + ":" + seconds;
	
	var message = event.data.split("|\|");
	
	var query = document.querySelector('#chatarea');
	
	if (message[0] != re_send) {
		
		var icon = document.createElement('span');
		icon.setAttribute("class", "glyphicon glyphicon-user");
		icon.setAttribute("aria-hidden", "true");
		icon.style["color"] = "gray";
		icon.style["width"] = "15px;";
		icon.style["height"] = "15px;";
		
		var who = document.createElement('button');
		who.setAttribute("class","btn btn-default");
		who.setAttribute("data-toggle", "tooltip");
		who.setAttribute("data-placement", "right");
		who.setAttribute("title", realTime);
		who.style["font-weight"] = "bold";
		who.appendChild(icon);
		who.append(message[0]);
		query.appendChild(who);
		
		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		query.appendChild(clear);

		re_send = message[0];

	}

	var div = document.createElement('div');
	div.setAttribute("data-toggle", "tooltip");
	div.setAttribute("data-placement", "right");
	div.setAttribute("title", realTime);
	div.style["display"] = "inline-block";
	div.style["padding-left"] = "10px";
	div.style["padding-top"] = "3px;"
	div.style["padding-bottom"] = "3px;"
	div.innerHTML = message[1];
	
	query.appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	query.appendChild(clear);

	const $messageTextBox = $('#chatarea'); 
	$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
	
	//chatarea.scrollTop = chatarea.scrollHeight;
	
	$('[data-toggle="tooltip"]').tooltip()

}
// 웹소켓에 접속시 실행되는 함수
function onOpen() {
	var member_name = $('#member_name').val();

	var query = document.querySelector('#chatarea');
	
	var div = document.createElement('div');
	div.style["float"] = "left";

	div.innerHTML = member_name + "님 이 채팅방에 입장하였습니다.";
	query.appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	query.appendChild(clear);

	webSocket.send(member_name + "님이 채팅방에 입장하였습니다.|\|메세지를 보내주세요.");

}
// 웹소켓 에러시 실행되는 함수
function onError(event) {

	console.log("서버 연결 에러" + event.data);
}
// 내가 보낸 메세지 내 화면에 출력 및 onMessage로 해당 채팅값 전달
function send(msg) {
	
	var chat_content = msg;
	
	var today = new Date();
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate();  // 날짜
	
	var hours = today.getHours(); // 시
	var minutes = today.getMinutes();  // 분
	var seconds = today.getSeconds();  // 초
	
	var realTime = month + "월 " + date +"일 "+ hours + ":" + minutes + ":" + seconds;

	if (chat_content.value != "") {
		
		var member_name = $("#member_name").val();
			//onMessage로 보내는 웹소켓 메소드
			webSocket.send(member_name + "|\|" + chat_content);
			var query = document.querySelector('#chatarea');
			if (member_name != re_send) {
				
				var icon = document.createElement('span');
				icon.setAttribute("class","glyphicon glyphicon-user");
				icon.setAttribute("aria-hidden", "true");
				icon.style["color"] = "gold";
				icon.style["width"] = "15px;";
				icon.style["height"] = "15px;";
				
				var who = document.createElement('button');
				who.setAttribute("class","btn btn-default");
				who.setAttribute("data-toggle", "tooltip");
				who.setAttribute("data-placement", "right");
				who.setAttribute("title", realTime);
				who.style["font-weight"] = "bold";
				who.appendChild(icon);
				who.append(member_name);
				
				query.appendChild(who);
				
				var clear = document.createElement('div');
				clear.style["clear"] = "both";
				query.appendChild(clear);

				re_send = member_name;

			}

			var div = document.createElement('div');
			div.setAttribute("data-toggle", "tooltip");
			div.setAttribute("data-placement", "right");
			div.setAttribute("title", realTime);
			div.style["display"] = "inline-block";
			div.style["padding-left"] = "10px";
			div.style["padding-top"] = "3px;"
			div.style["padding-bottom"] = "3px;"
			div.innerHTML = chat_content;
			query.appendChild(div);

			var clear = document.createElement('div');
			clear.style["clear"] = "both";
			query.appendChild(clear);

			chat_content.value = '';

			
			// 채팅 append 후에 스크롤 최하단으로 내리기
			const $messageTextBox = $('#chatarea'); 
			$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
			//chatarea.scrollTop = chatarea.scrollHeight;

			re_send = member_name;
		}

	$('[data-toggle="tooltip"]').tooltip()
	
	}

function addmembername() {
	
	 var member_name = $("input[name=member_name]").val();
	 $("#member_name").val(member_name);
	
}
	