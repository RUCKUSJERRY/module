// 워크스페이스 리스트 호출해주는 함수 실행
$(function(){
	var member_num = $("#member_num").val();
	
	
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
						a.setAttribute("href","ChannelController?command=channelList&member_num="+list[0][i].member_num+"&workspace_num="+list[0][i].workspace_num);
						a.innerHTML = list[0][i].workspace_name;
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("onclick","workspaceDelcon("+list[0][i].workspace_num+")");
						button.innerHTML = "삭제";
						li.appendChild(a);
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
$(document).ready(function() {  
	// 워크스페이스 생성 모달 안에 맴버넘버 히든 인풋으로 넣어주기
	var member_num = $("#member_num").val();
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_num");
	input.value = member_num;
	var res = document.querySelector('#workspaceCommand');
	res.appendChild(input);
});
//워크스페이스 삭제 확인 함수
function workspaceDelcon(wsnum) {
	
	var con = confirm("정말로 채널을 삭제하시겠습니까?");
	var workspace_num = wsnum;
	
	if (con) {
		location.href='WorkSpaceController?command=delWorkSpace&workspace_num='+workspace_num;
	}
}
