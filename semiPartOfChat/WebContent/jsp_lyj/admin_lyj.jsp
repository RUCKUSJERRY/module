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
	<div class="container-fluid" id="header_container">
		<div id="nav_channel_info">
			<input type="text" id="member_num" value="${loginDto.member_num }">
			<input type="text" id="member_id" value="${loginDto.member_id }">
			<input type="text" id="member_pw" value="${loginDto.member_pw }">
			<input type="text" id="member_name" value="${loginDto.member_name }">
		</div>	
	</div>
</nav>
<div id="main_container" class="container-fluid">
	<div class="col-md-4" id="nav_container" >
		메뉴 영역
		<div id="nav_menu_list">
		<div id="nav_channel_list">채널</div>
		<div id="nav_massage_list">메세지</div>
		</div>
	</div>
	<div class="col-md-8" id="content_container" >
		컨텐츠 영역
		<div>채팅창</div>
		<div>텍스트에디터</div>
	</div>

</div>
<footer>
	<div class="container">
	<div id="map" style="width:500px; height:400px;"></div>
	</div>
</footer>
</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8b23fbdf7184ddeccfecb57797fda53f"></script>
<script type="text/javascript">
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

</script>
</html>