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
<link rel="icon" href="resources/imgage/favicon.ico">
<!-- 부트스트랩 -->
<script src="resources/js/jquery-3.6.0.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/bootstrap.min.js"></script>
<!-- workspace js -->
<script src="./resources/js/workspace.js"></script>
<title>Channel WorkSpace</title>
<style type="text/css">
	body {
	  padding-top: 40px;
	  padding-bottom: 40px;
	  background-color: white;
	}
	
	.form-signin {
	  max-width: 330px;
	  padding: 15px;
	  margin: 0 auto;
	  text-align: center;
	}
	.form-signin .form-signin-heading,
	.form-signin .checkbox {
	  margin-bottom: 10px;
	}
	.form-signin .checkbox {
	  font-weight: normal;
	}
	.form-signin .form-control {
	  position: relative;
	  height: auto;
	  box-sizing: border-box;
	  padding: 10px;
	  font-size: 16px;
	}
	.form-signin .form-control:focus {
	  z-index: 2;
	}
	.form-signin input[type="email"] {
	  margin-bottom: -1px;
	  border-bottom-right-radius: 0;
	  border-bottom-left-radius: 0;
	}
	.form-signin input[type="password"] {
	  margin-bottom: 10px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}
	
	#insert_btn{
		background-color: white;
		color: blue;
	}
	img {
		width: 82%;
		height: auto;
		box-shadow: 0.5px 0.5px;
		border-radius:10px;
		
	}
	
	span {
		color: rgb(30, 30, 30);
	}
	
	h4 {
		color: rgb(120, 120, 120);
	}
	
	
        a,
        a:focus,
        a:hover {
            color: gray;
        }

        .btn-default,
        .btn-default:hover,
        .btn-default:focus {
            color: #333;
            text-shadow: none;
        }

        html,
        body {
            height: 98%;
            background-color: #333;
            margin-top: 25px;

        }

        body {
            color: #black;
            text-align: center;
            padding-top: 0px;
            background-image: url('resources/image/bg1.jpg');
        	background-size: cover;
        	background-color:transparent;
        }

        #navbox {
            background-color: black;
            color: white;
            font-weight: bold;

        }
        
        #space {
        	display:block;
        	top: 20%;
        }
	
</style>
</head>
<body>
	<input type="hidden" id="member_num" value="${loginDto.member_num }">
	<input type="hidden" id="member_id" value="${loginDto.member_id }">
	<input type="hidden" id="member_pw" value="${loginDto.member_pw }">
	<input type="hidden" id="member_name" value="${loginDto.member_name }">
	
	<nav class="navbar navbar-default navbar-fixed-top" id="navbox">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Channel</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index.html">시작하기</a></li>
                    <li><a href="">Channel이란?</a></li>
                    <li><a href="">이용방법</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="MemberController?command=member_insert_page">회원가입</a></li>
                    <li id="loginChange"><a href="MemberController?command=member_login_page">Login </a></li>
                </ul>
            </div>
        </div>
    </nav>

	<div class="col-xs-12 " id="workspace">
		<div class="panel-group" role="tablist">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="workspaceheading">
					<h2 class="panel-title" id="workspacebtn">
						<a class="collapsed" data-toggle="collapse" href="#workspaceList"
							aria-expanded="false" aria-controls="workspaceList">
							워크스페이스 List
							<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
							</a> 
					</h2>
				</div>
				<div id="workspaceList" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="workspaceheading"
					aria-expanded="false" style="height: 0px;">
					<ul class="list-group" id="selectWorkSpaceList">
					</ul>
					<div class="panel-footer">
						<button type="button" 
						class="btn btn-default btn-lg btn-block"
						data-toggle="modal" data-target="#addWorkSpaceForm">새 워크스페이스 생성</button>
		
					</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="addWorkSpaceForm" tabindex="-1" role="dialog" aria-labelledby="addChannelLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addChannelLable">새 워크스페이스 추가</h3>
				</div>
				<div class="modal-body">
					<form action="ChannelController" method="post" id="workspaceAddSubmit">
						<input type="hidden" name="command" value="WorkSpaceAdd"> 
						<input type="hidden" name="member_id" value="${loginDto.member_id }">
						<input type="hidden" name="member_name" value="${loginDto.member_name }">
						<div class="form-group">
							<label for="recipient-name" class="control-label">회사명을 입력해주세요.</label>
							<input type="text" class="form-control" name="workspace_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">무슨 일을 하는 회사인가요? 회사정보입력</label>
							<textarea class="form-control" name="workspace_information"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary" form="workspaceAddSubmit">생성하기</button>
				</div>
			</div>
		</div>
	</div>
		
</body>
</html>