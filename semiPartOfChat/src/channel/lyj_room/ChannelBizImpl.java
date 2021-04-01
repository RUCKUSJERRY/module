package channel.lyj_room;

import java.util.List;

import channel.member.dto.MemberDto;

public class ChannelBizImpl implements ChannelBiz {

	ChannelDao dao = new ChannelDaoImpl();

	// WorkSpace CRUD
	// 1. 워크스페이스 생성
	@Override
	public int createWorkSpace(WorkSpaceDto wsDto) {
		return dao.createWorkSpace(wsDto);
	}

	// 2. 워크스페이스 맴버 추가
	@Override
	public int insertWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		return dao.insertWorkSpaceMember(wsmemDto);
	}

	// 3. 워크스페이스 맴버 제거
	@Override
	public int deleteWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		
		return 0;
	}

	// 4. 워크스페이스 정보 수정
	@Override
	public int updateWorkSpace(WorkSpaceDto wsDto) {
		
		return 0;
	}

	// 5. 워크스페이스 삭제
	@Override
	public int deleteWorkSpace(int workspace_seq) {
		int res1 = dao.deleteWorkSpace(workspace_seq);
		int res2 = dao.deleteWorkSpaceAllMember(workspace_seq);
		
		return res1 + res2;
	}

	// 6. 전체 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectAllWorkSpace() {
		
		return null;
	}

	// 7. 해당 회원이 등록되어있는 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectMemberWorkSpace(String member_id) {
		
		return dao.selectMemberWorkSpace(member_id);
	}

	// 8. 1개 워크스페이스 출력
	@Override
	public WorkSpaceDto selectOneWorkSpace(WorkSpaceDto wsDto) {
		
		return null;
	}
	
	// 9. 가장 최근에 생성된 워크스페이스 번호
	@Override
	public int getLastWorkSpaceSeq() {
		return dao.getLastWorkSpaceSeq();
	}
	
	// 10. 해당 워크스페이스의 맴버 리스트 호출 (본인 제외)
	@Override
	public List<WorkSpaceMemberDto> callWorkspaceMemberList(WorkSpaceMemberDto wsmemDto) {
		return dao.callWorkspaceMemberList(wsmemDto);
	}
	
	// 11. 워크스페이스 초대 맴버 리스트
	@Override
	public List<MemberDto> callWorkspaceInviteList(int workspace_seq) {
		return dao.callWorkspaceInviteList(workspace_seq);
	}
	
	// 12. 체크한 맴버들 워크스페이스로 초대
	@Override
	public int inviteWorkspace(WorkSpaceMemberDto dto) {
		return dao.inviteWorkspace(dto);
	}
	
	// 13. 체크한 맴버들 워크프세이스에서 추방
	@Override
	public int banishWorkspace(WorkSpaceMemberDto dto) {
		return dao.banishWorkspace(dto);
	}
	
	
	// Channel CRUD

	

	@Override
	public int createChannel(ChannelDto dto) {
		return dao.createChannel(dto);
	}

	@Override
	public int channelUpdate(ChannelDto dto) {
		return dao.channelUpdate(dto);
	}

	@Override
	public int channelDelete(int channel_num) {
		return dao.channelDelete(channel_num);
	}

	@Override
	public List<ChannelDto> channelAdminList() {
		return dao.channelAdminList();
	}

	@Override
	public List<ChannelDto> channelList(ChannelDto chDto) {
		return dao.channelList(chDto);
	}

	@Override
	public ChannelDto channelSelect(int channel_num) {
		return dao.channelSelect(channel_num);
	}
	// 6. 채널참여자 리스트에 인서트
	@Override
	public int channelMemberAdd(ChannelMemberDto chmemDto) {
		return dao.channelMemberAdd(chmemDto);
	}

	@Override
	public List<MemberDto> channelMemeberList(String channel_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> allMemeberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChannelMemberDto> otherMemeberList(String channel_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delChannelMember(ChannelMemberDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String delIdCheck(int channel_num) {
		return dao.delIdCheck(channel_num);
	}
	
	@Override
	public int getLastChannelSeq() {
		return dao.getLastChannelSeq();
	}
	
	// Message CRUD
	// 1. 메세지 초대 리스트 불러오기
	@Override
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto) {
		return dao.callInviteMessageMemberList(wsmemDto);
	}
	// 2. 메세지 룸 생성
	@Override
	public int createMessageRoom(MessageRoomDto dto) {
		return dao.createMessageRoom(dto);
	}
	
}
