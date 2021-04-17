<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
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

<%

    String NclientId = "_ux_53Q_FFO4HwCKyRH0";//애플리케이션 클라이언트 아이디값";
    String NredirectURI = URLEncoder.encode("http://localhost:8787/SNS_Login/LoginController?command=naverlogin", "UTF-8");
    SecureRandom Nrandom = new SecureRandom();
    String Nstate = new BigInteger(130, Nrandom).toString();
    String NapiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    NapiURL += "&client_id=" + NclientId;
    NapiURL += "&redirect_uri=" + NredirectURI;
    NapiURL += "&state=" + Nstate;
    session.setAttribute("Nstate", Nstate);
    	
	String GclientId = "533483186463-pa8l2qj040k6tcb3so8co0in080qv3c9.apps.googleusercontent.com";
	String GredirectURI = URLEncoder.encode("http://localhost:8787/SNS_Login/LoginController?command=googlelogin", "UTF-8");
	String GapiUrl = "https://accounts.google.com/o/oauth2/auth?client_id="+ GclientId
	 				+ "&redirect_uri="+ GredirectURI 
	 				+ "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email&approval_prompt=force&access_type=offline";

%>


	<a href=<%=NapiURL %>>네이버 로그인</a>
	<br><br>		
			
	<a href="<%=GapiUrl%>">구글 로그인</a>
			



</body>
</html>