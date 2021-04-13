<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- include summernote css/js -->
<link href="resources/summernote/summernote.css" rel="stylesheet">
<script src="resources/summernote/summernote.js"></script>
<title>WebsocketChat</title>
<style type="text/css">
/* 이용준 main css 수정 부분 */
#roominfo {
	height: 3em;

}

#chatarea {
	color: black;
	height: 45em;
	overflow: auto;
}

#textarea {
	height: 12em;
}

#chatarea::-webkit-scrollbar {
	width: 7px; /*스크롤바의 너비*/
}

#chatarea::-webkit-scrollbar-thumb {
	background-color: gray; /*스크롤바의 색상*/
	border-radius: 5px;
}

#chatarea::-webkit-scrollbar-track {
	background-color: white; /*스크롤바 트랙 색상*/
}

</style>
</head>
<body>
			<div id="channelcontentarea">
				<div id="roominfo" class="form-control">
					<input type="text" placeholder="작성자명입력해주세요" name="member_name">
					<input type="text" id="member_name">
					<input type="button" onclick="addmembername();" value="작성자지정">
				</div>
				<div id="chatarea" class="form-control">
				
				</div>
				<div id="textarea" class="form-control">
					<textarea id="summernote"></textarea>
				</div>
			</div>
<script type="text/javascript" src="resources/js/websocket.js"></script>
<script type="text/javascript" src="resources/js/callsummernote.js"></script>
</body>
</html>