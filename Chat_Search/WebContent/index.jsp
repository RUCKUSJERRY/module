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

<input type="text">
<input type="button" value="검색" onclick="searchbtn();">

<script type="text/javascript">

var content = document.getElementsByTagName("input")[0];
var btn = document.getElementsByTagName("input")[1];

function searchbtn(){
	location.href="SearchController?command=search&content="+content.value;
}


</script>






</body>
</html>