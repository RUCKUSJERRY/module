// 로그인되어있으면 로그인 버튼 Logout으로 문구 바꿔주기
$(function(){
	
	var member_id = $("#member_id").val();
	if (member_id != null) {
		
		$("#loginChange").children().remove();
		
		$("#loginChange").append("<a href='MemberController?command=logout'>Logout </a>"); 
		
	}
	
})
// 워크스페이스 리스트 불러오기
$(function(){

	var member_id = $("#member_id").val();

	console.log(member_id);
	if (member_id != null) {
		function  getParameterValues() {
			return "?command=selectMemberWorkSpace&member_id=" + member_id;
		}
		
		$.ajax({
			url:"ChannelController"+getParameterValues(),
			dataType: "json",
			method: "post",
			success:function(data){
				
				var list = data.result;

					for (var i = 0; i < list[0].length; i++) {
						
						var li = document.createElement('li');
						li.setAttribute("class","list-group-item");
						
						var a = document.createElement('a');
						a.setAttribute("href","ChannelController?command=channelList&member_id="+list[0][i].member_id+"&workspace_seq="+list[0][i].workspace_seq+"&workspace_name="+list[0][i].workspace_name);
						a.innerHTML = list[0][i].workspace_name;
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("onclick","workspaceDelcon("+list[0][i].workspace_seq+")");
						button.innerHTML = "삭제";
						li.appendChild(a);
						li.appendChild(button);

						$("#selectWorkSpaceList").append(li);				
					}
			},
			error: function(){
				alert("워크스페이스 불러오기 실패")
			}		
		})		
	}	
})

//워크스페이스 삭제 확인 함수
function workspaceDelcon(wsseq) {
	
	var con = confirm("정말로 채널을 삭제하시겠습니까?");
	var workspace_seq = wsseq;
	
	if (con) {
		location.href='ChannelController?command=WorkSpaceDel&workspace_seq='+workspace_seq;
	}
}


