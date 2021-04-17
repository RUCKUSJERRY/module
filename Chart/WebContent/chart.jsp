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
<title>Chart</title>
</head>
<body>
		<h1>차트 예시</h1>
		<h3>연도별 재산 목표 (단위:백만원)</h3><br>
		
		<span>2021년 목표</span><input type="text" id="2021" placeholder="2021"><br>
		<span>2022년 목표</span><input type="text" id="2022" placeholder="2022"><br>
		<span>2023년 목표</span><input type="text" id="2023" placeholder="2023"><br>
		
		<input type="button" value="차트작성" onclick="callChart();">
		
        <div id="chart" style="width: 100em; height: 40em"></div>

</body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="resources/js/chart.js"></script>

</html>