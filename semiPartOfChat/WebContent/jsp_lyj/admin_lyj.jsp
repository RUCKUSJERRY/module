<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHANNEL_ADMIN</title>
<link href="./resources/css/main_lyj.css" rel="stylesheet">
<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="./resources/summernote/summernote.css" rel="stylesheet">
<script src="./resources/summernote/summernote.js"></script>
</head>
<body>
<nav>
	<div class="container" id="header_container">
		<div id="nav_channel_info">
			<input type="text" id="member_num" value="${loginDto.member_num }">
			<input type="text" id="member_id" value="${loginDto.member_id }">
			<input type="text" id="member_pw" value="${loginDto.member_pw }">
			<input type="text" id="member_name" value="${loginDto.member_name }">
		</div>	
	</div>
</nav>
<div id="main_container" class="container">
	<div id="nav_container">
		<div id="nav_menu_list">
		<div id="nav_channel_list">채널</div>
		<div id="nav_massage_list">메세지</div>
		</div>
	</div>
	<div id="content_container">
		<div></div>
	</div>

</div>
<footer>
	<div class="container">
	</div>
</footer>


</body>
</html>