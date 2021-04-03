// 워크스페이스 리스트 호출해주는 함수 실행
$(function(){
	var member_num = $("#member_num").val();
	
	// 워크스페이스 생성 모달 안에 맴버넘버 히든 인풋으로 넣어주기
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_num");
	input.value = member_num;
	var res = document.querySelector('#workspaceCommand');
	res.appendChild(input);
	
	if (member_num != null) {
		function  getParameterValues() {
			return "?command=selectMemberWorkSpace&member_num=" + member_num;
		}
		
		$.ajax({
			url:"WorkSpaceController"+getParameterValues(),
			dataType: "json",
			method: "post",
			success:function(data){
				
				var list = data.result;
				if (list != null) {
					for (var i = 0; i < list[0].length; i++) {
						
						var li = document.createElement('li');
						li.setAttribute("class","list-group-item");
						
						var a = document.createElement('a');
						a.setAttribute("href","ChannelController?command=channelIn&member_num="+list[0][i].member_num+"&workspace_num="+list[0][i].workspace_num);
						a.innerHTML = list[0][i].workspace_name;
						li.appendChild(a);

						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("data-toggle","modal");
						button.setAttribute("data-target","#workspaceAdminForm");
						button.setAttribute("onclick","selectWorkspaceMemberList("+list[0][i].workspace_num+")");
						button.innerHTML = "관리";
						li.appendChild(button);
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("onclick","workspaceDelcon("+list[0][i].workspace_num+")");
						button.innerHTML = "삭제";
						li.appendChild(button);

						$("#workspaceArea").append(li);				
					}
				} else {
					
					var p = document.createElement('p');
					p.innerHTML = "워크스페이스가 없습니다. 새로 워크스페이스를 생성해주세요.";
					$("#workspaceArea").append(p);	
					
				}
					
			},
			error: function(){
				alert("워크스페이스 불러오기 실패")
			}		
		})		
	} else {
		alert("로그인 세션이 만료되었습니다. 다시 로그인해주세요.")
		location.href="member_login.jsp";
	}

})
//워크스페이스 삭제 확인 함수
function workspaceDelcon(wsnum) {
	
	var con = confirm("정말로 채널을 삭제하시겠습니까?");
	var workspace_num = wsnum;
	
	if (con) {
		location.href='WorkSpaceController?command=delWorkSpace&workspace_num='+workspace_num;
	}
}
//워크스페이스 관리 클릭시 해당 워크스페이스의 맴버 목록 호출
function selectWorkspaceMemberList(wsnum) {

	var workspace_num = wsnum;
	var member_num = $("#member_num").val();

	function getParameterValues() {

		return "?command=selectWorkspaceMemberList&workspace_num="
				+ workspace_num + "&member_num=" + member_num;
	}

	$.ajax({
		url : "WorkSpaceController" + getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			console.log(list);
			$("#workspaceMemberList").children().remove();
			$("#workspaceInviteList").children().remove();

			var ul = document.createElement('ul');
			ul.setAttribute("class", "list-group");
			document.getElementById('workspaceMemberList').appendChild(ul);

			for (var i = 0; i < list[0].length; i++) {
				var member_num = list[0][i].member_num;
				var member_id = list[0][i].member_id;
				var member_name = list[0][i].member_name;

				var input = document.createElement('input');
				input.id = "member_num";
				input.type = "hidden";
				input.value = member_num;
				document.getElementById('workspaceMemberList').appendChild(
						input);

				var li = document.createElement('li');
				li.setAttribute("class", "list-group-item");

				var div = document.createElement('div');
				div.setAttribute("class", "input-group");

				var span = document.createElement('span');
				span.setAttribute("class", "input-group-addon");

				var input = document.createElement('input');
				input.id = "member_id" + (i + 1);
				input.type = "checkbox";
				input.name = "banishChk";
				input.value = member_id;
				span.appendChild(input);

				var input = document.createElement('input');
				input.id = "member_name" + (i + 1);
				input.type = "hidden";
				input.value = member_name;
				span.appendChild(input);

				div.appendChild(span);

				var input = document.createElement('input');

				input.type = "text";
				input.value = "이름(ID) : " + member_name + " ( " + member_id
						+ " ) ";
				input.setAttribute("readonly", "readonly");
				input.setAttribute("class", "form-control");
				input.style["background-color"] = "white";
				div.appendChild(input);
				li.appendChild(div);

				document.getElementById('workspaceMemberList').appendChild(li);
			}
		},
		error : function() {
			alert("통신 실패")
		}

	})

}


