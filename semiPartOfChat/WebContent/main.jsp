<%@page import="channel.lyj_room.MessageRoomDto"%>
<%@page import="channel.lyj_room.WorkSpaceDto"%>
<%@page import="channel.lyj_room.ChannelDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Channel Main Page</title>

<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/member_statement.js"></script>

<!-- include summernote css/js -->
<link href="./resources/summernote/summernote.css" rel="stylesheet">
<script src="./resources/summernote/summernote.js"></script>
<style>
body {
	padding-top: 50px;
}

.sub-header {
	padding-bottom: 10px;
	border-bottom: 1px solid #eee;
}

.navbar-fixed-top {
	border: 0;
}

.sidebar {
	display: none;
}

@media ( min-width : 768px) {
	.sidebar {
		position: fixed;
		top: 51px;
		bottom: 0;
		left: 0;
		z-index: 1000;
		display: block;
		padding: 20px;
		overflow-x: hidden;
		overflow-y: auto;
		background-color: #f5f5f5;
		border-right: 1px solid #eee;
	}
}

.nav-sidebar {
	margin-right: -21px;
	margin-bottom: 20px;
	margin-left: -20px;
}

.nav-sidebar>li>a {
	padding-right: 20px;
	padding-left: 20px;
}

.nav-sidebar>.active>a, .nav-sidebar>.active>a:hover, .nav-sidebar>.active>a:focus
	{
	color: #fff;
	background-color: #428bca;
}

.main {
	padding: 20px;
}

@media ( min-width : 768px) {
	.main {
		padding-right: 40px;
		padding-left: 40px;
	}
}

.main .page-header {
	margin-top: 0;
}

.placeholders {
	margin-bottom: 30px;
	text-align: center;
}

.placeholders h4 {
	margin-bottom: 0;
}

.placeholder {
	margin-bottom: 20px;
}

.placeholder img {
	display: inline-block;
	border-radius: 50%;
}

#side-navbar a {
	color: black;
	font-size: 1.5rem;
}

/* 이용준 main css 수정 부분 */
#roominfo {
	border-right: 1px solid gray;
	border-left: 1px solid gray;
	border-bottom: 1px solid gray;
	height: 5em;
}

#chatarea {
	background-color: white;
	color: black;
	height: 45em;
	border-left: 1px solid gray;
	border-right: 1px solid gray;
	overflow: auto;
}

#textarea {
	background-color: white;
	border-left: 1px solid gray;
	border-right: 1px solid gray;
	color: black;
	border-right: 1px solid gray;
	color: black;
	height: 10em;
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
<%
	
	int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
	String workspace_name = request.getParameter("workspace_name");
	
	List<ChannelDto> chList = (List<ChannelDto>) request.getAttribute("channelList");
	List<MessageRoomDto> msgList = (List<MessageRoomDto>) request.getAttribute("messageRoomList");
		
%>
	<input type="hidden" id="member_num" value="${loginDto.member_num }">
	<input type="hidden" id="member_id" value="${loginDto.member_id }">
	<input type="hidden" id="member_pw" value="${loginDto.member_pw }">
	<input type="hidden" id="member_name" value="${loginDto.member_name }">
	<input type="hidden" id="member_email" value="${loginDto.member_email }">
	<input type="hidden" id="member_phone" value="${loginDto.member_phone }">
	<input type="hidden" id="member_pscode" value="${loginDto.member_pscode }">
	<input type="hidden" id="member_addr" value="${loginDto.member_addr }">
	<input type="hidden" id="member_addrdt" value="${loginDto.member_addrdt }">
	<input type="hidden" id="member_type" value="${loginDto.member_type }">
	<input type="hidden" id="member_auth" value="${loginDto.member_auth }">
	<input type="hidden" id="member_date" value="${loginDto.member_date }">
	<input type="hidden" id="member_enabled" value="${loginDto.member_enabled }">
	<input type="hidden" id="member_statement" value="${loginDto.member_statement }">
	<input type="hidden" id="workspace_seq" value="<%=workspace_seq %>">
	<input type="hidden" id="workspace_name" value="<%=workspace_name %>">
	<input type="hidden" id="channel_seq_onload" value="<%=chList.get(0).getChannel_seq()%>">
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Top Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<button style="float: left;" type="button"
					class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#side-navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Side Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Channel</a>

			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a style="font-size: 1.5rem;" href="">&#128365; alarm</a></li>
					<li><a style="font-size: 1.5rem;" href="">&#128100; my</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div id="side-navbar" class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="">Main</a></li>
					<li>
						<div class="panel-heading" role="tab" id="collapseListWorkspace">
							<h4 class="panel-title" id="-collapsible-list-group-">
								<a class="collapsed" data-toggle="collapse" href="#workspacelist"
									aria-expanded="false" aria-controls="workspacelist">Workspace </a>
							</h4>
						</div>
						<div id="workspacelist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListWorkspace"
							aria-expanded="false" style="height: 0px;">
							<ul class="list-group">
								<li class="list-group-item">
									<button type="button" class="btn btn-default btn-lg btn-block"
										data-toggle="modal" data-target="#workspaceAdminForm" onclick="callWorkspaceMemberList()">워크스페이스 관리</button>
								</li>
								<li class="list-group-item">
									<button type="button" class="btn btn-default btn-lg btn-block"
										onclick="workspaceDelcon(<%=workspace_seq %>);">워크스페이스 삭제</button>
								</li>
								<li class="list-group-item">
									<button type="button" class="btn btn-default btn-lg btn-block"
										onclick="location.href='ChannelController?command=WorkSpace'">Change Workspace</button>
								</li>
								</ul>
							</div>
						</li>
					<li>
						<div class="panel-heading" role="tab" id="collapseListChannel">
							<h4 class="panel-title" id="-collapsible-list-group-">
								<a class="collapsed" data-toggle="collapse" href="#channellist"
									aria-expanded="false" aria-controls="channellist">Channel </a>
							</h4>
						</div>
						<div id="channellist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListChannel"
							aria-expanded="false" style="height: 0px;">
							<ul class="list-group">
								<%
								if (chList.size() == 0) {
								%>
								<li class="list-group-item">채널이 없습니다.<br>채널을 추가해보세요.
								</li>
								<%
								} else {
								%>
								<li class="list-group-item">
								<button type="button" class="btn btn-default btn-lg btn-block"
										onclick="callChatList(<%=chList.get(0).getChannel_seq()%>);"><%=chList.get(0).getChannel_name()%></button>
								</li>
								<%
								for (int i = 1; i < chList.size(); i++) {
								%>
								<li class="list-group-item">
								<button type="button" class="btn btn-default btn-lg"
										onclick="callChatList(<%=chList.get(i).getChannel_seq()%>);"><%=chList.get(i).getChannel_name()%></button>
								<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#adminChannelForm"
								onclick="channelAdmin(<%=chList.get(i).getChannel_seq()%>, '<%=chList.get(i).getChannel_name()%>', '<%=chList.get(i).getChannel_information()%>', '<%=chList.get(i).getChannel_access()%>');">
								관리
								</button>
								<button type="button" class="btn btn-default btn-sm" onclick="channeldelcon(<%=chList.get(i).getChannel_seq()%>);">
								삭제
								</button>
								</li>
								<%
								}

								}
								%>
								<li class="list-group-item">
									<button type="button" class="btn btn-default btn-lg btn-block"
										data-toggle="modal" data-target="#addChannelForm">채널추가</button>
								</li>
							</ul>
						</div>
					</li>
					<li>
					<div class="panel-heading" role="tab" id="collapseListChannel">
							<h4 class="panel-title" id="-collapsible-list-group-">
								<a class="collapsed" data-toggle="collapse" href="#messagelist"
									aria-expanded="false" aria-controls="messagelist">Message </a>
							</h4>
						</div>
						<div id="messagelist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListChannel"
							aria-expanded="false" style="height: 0px;">
							<ul class="list-group">
								<%
								if (msgList.size() == 0) {
								%>
								<li class="list-group-item">메세지가 없습니다.<br>메세지를 보내보세요.
								</li>
								<%
								} else {

								for (int i = 0; i < msgList.size(); i++) {
								%>
								<li class="list-group-item">
								<button type="button" class="btn btn-default btn-lg"
										onclick="callMessageList(<%=msgList.get(i).getMessageroom_seq() %>);"><%=msgList.get(i).getMember_id() + ":" + msgList.get(i).getMember2_id() %></button>
								<button type="button" class="btn btn-default btn-xs" onclick="messagedelcon(<%=msgList.get(i).getMessageroom_seq() %>);">
								삭제
								</button>
								</li>
								<%
									}

								}
								%>
								<li class="list-group-item">
									<button type="button" class="btn btn-default btn-lg btn-block"
										data-toggle="modal" data-target="#addMessageForm" onclick="callInviteMessageMemberList();">새 메세지</button>
								</li>
							</ul>
						</div>
					</li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">지도</a></li>
					<li><a href="">날씨</a></li>
					<li><a href="">covid</a></li>
					<li><a href="">그림판</a></li>
					<li><a href="">번역</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">화상통화</a></li>
					<li><a href="">회원정보 수정</a></li>
					<li><a href="">LogOut</a></li>
				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="col-xs-12" id="content_container">
					<div class="" id="roominfo"></div>
					<div class="" id="chatarea"></div>
					<div class="" id="textarea">
						<textarea id="summernote"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="addChannelForm" tabindex="-1" role="dialog"	aria-labelledby="addChannelLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addChannelLable">채널 추가</h3>
				</div>
				<div class="modal-body">
					<form action="ChannelController" method="post" id="channelAddSubmit">
						<input type="hidden" name="command" value="channelAdd"> 
						<input type="hidden" name="member_id" value="${loginDto.member_id }">
						<input type="hidden" name="member_name" value="${loginDto.member_name }">
						<input type="hidden" name="workspace_seq" value="<%=workspace_seq %>">
						<div class="form-group">
							<label for="recipient-name" class="control-label">채널명</label>
							<input type="text" class="form-control" name="channel_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">채널정보</label>
							<textarea class="form-control" name="channel_information"></textarea>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label" >공개/비공개 여부</label>
						<div class="form-group">
							<select name="channel_access">
								<option value="PUBLIC">공개</option>
								<option value="PRIVATE">비공개</option>
							</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary" form="channelAddSubmit">채널추가</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="adminChannelForm" tabindex="-1" role="dialog"	aria-labelledby="adminChannelLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="adminChannelLable">채널 수정</h3>
				</div>
				<div class="modal-body">
					<form action="ChannelController" method="post" id="channelUpdateSubmit">
						<input type="hidden" name="command" value="channelUpdate"> 
						<input type="hidden" name="member_id" value="${loginDto.member_id }">
						<input type="hidden" name="member_name" value="${loginDto.member_name }">
						<input type="hidden" name="workspace_seq" value="<%=workspace_seq %>">
						<input type="hidden" name="channel_seq" id="update_channel_seq">
						<div class="form-group">
							<label for="recipient-name" class="control-label">채널명</label>
							<input type="text" class="form-control" name="channel_name" id="update_channel_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">채널정보</label>
							<textarea class="form-control" name="channel_information" id="update_channel_information"></textarea>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label" >공개/비공개 여부</label>
						<div class="form-group">
							<select name="channel_access" id="update_channel_access">
								<option value="PUBLIC">공개</option>
								<option value="PRIVATE">비공개</option>
							</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" form="channelUpdateSubmit">정보수정</button>
				</div>
				<div class="modal-body">
					<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="채널 맴버 목록" onclick="callChannelMemberList()"></label>
						<div id="channelMemberList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="banishChannel();">채널추방</button>
				</div>	
				<div class="modal-body">
					<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="채널 맴버 초대" onclick="callChannelInviteList();"></label>	
						<div id="channelInviteList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="adminChannelCancel">취소</button>
					<button type="submit" class="btn btn-primary" onclick="inviteChannel()">채널초대</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="addMessageForm" tabindex="-1" role="dialog"	aria-labelledby="addMessageLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addMessageLable">새 메세지 생성</h3>
				</div>
				<div class="modal-body">
					<label for="recipient-name" class="control-label">메세지 가능 리스트</label>
						<div id="messageInviteMemberList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="addMessageCancel">취소</button>
					<button type="submit" class="btn btn-primary" onclick="createMessageRoom();" >메세지 보내기</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="workspaceAdminForm" tabindex="-1" role="dialog"	aria-labelledby="addWorkspaceLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addWorkspaceLable">워크스페이스 관리</h3>
				</div>
				<div class="modal-body">
					<label for="recipient-name" class="control-label"><input class="btn btn-default" onclick="callWorkspaceMemberList()" type="button" value="맴버 목록"></label>
						<div id="workspaceMemberList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="banishWorkspace();">추방하기</button>
				</div>
				
				<div class="modal-body">
					
					<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="맴버 초대" onclick="callWorkspaceInviteList();"></label>	
						<div id="workspaceInviteList">
							
						</div>
						
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="adminWorkspaceCancel">취소</button>
					<button type="submit" class="btn btn-primary" onclick="inviteWorkspace()">초대하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- 채팅관련 js -->
<script src="./resources/js/main.js"></script>
</html>