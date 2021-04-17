<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHANNEL / OrderSheet / ${loginDto.member_name }</title>
<style>
span {
	width: 60px;
	display: inline-block;
}

textarea {
	width: 40%;
	height: 280px;
}
</style>



</head>
<body>

	
	
<h2>CHANNEL 이용료 결제</h2>

	<form action="pay_credit_checkout.jsp" method="post">
	<input type="hidden" name="type" value="CREDIT">
		<p>
			<span>name:</span> <input type="text" name="name" placeholder="이름을 입력해주세요" autofocus="autofocus">


			<!-- 값 가져오기 -->
		</p>
		<p>
			<span>이메일:</span> <input type="text" name="email" placeholder="이메일을 입력해주세요">
			
		</p>
		
		<p>
			<span>폰넘버:</span><input type="text" name="phone" placeholder="연락처를 입력해주세요">
		</p>
		
		<p>
			<span>가격:</span><input name="totalPrice" placeholder="금액을 입력해주세요">
		</p>
		<input type="submit" value="결제하기"> 
		<input type="reset"	value="취소하기">
	</form>


</body>
</html>