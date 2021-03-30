// 로그인되어있으면 로그인 버튼 Logout으로 문구 바꿔주기
$(function(){
	
	var member_id = $("#member_id").val();
	if (member_id != null) {
		
		$("#loginChange").children().remove();
		
		$("#loginChange").append("<a href='MemberController?command=logout'>Logout </a>"); 
		
	}
	
})

// 워크스페이스 리스트 추가하기

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
				// id = selectWorkSpaceList
				// <li class="list-group-item"></li>
				
				var list = data.result;

					for (var i = 0; i < list[0].length; i++) {
						
						var $li = 
						$("<li class='list-group-item'>"
						+"<a href='location.href=ChannelController?command=channelList&member_id="+list[0][i].member_id+"'>"
						+list[0][i].workspace_name +"</a></li>")
						
						$("#selectWorkSpaceList").append($li);
						
						
					}
			
			},
			error: function(){
				alert("워크스페이스 불러오기 실패")
			}
			
			
		})
		
		
	}
	
})
