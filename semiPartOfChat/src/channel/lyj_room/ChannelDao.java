package channel.lyj_room;

import java.util.List;

import channel.member.dto.MemberDto;

public interface ChannelDao {

	// WorkSpace CRUD

	// 1. 워크스페이스 생성
	public int createWorkSpace(WorkSpaceDto wsDto);
	// 2. 워크스페이스 맴버 추가
	public int insertWorkSpaceMember(WorkSpaceMemberDto wsmemDto);
	// 3. 워크스페이스 맴버 제거
	public int deleteWorkSpaceMember(WorkSpaceMemberDto wsmemDto);
	// 4. 워크스페이스 정보 수정
	public int updateWorkSpace(WorkSpaceDto wsDto);
	// 5. 워크스페이스 삭제
	public int deleteWorkSpace(int workspace_seq);
	// 5-1. 삭제하는 워크스페이스의 맴버 전원 삭제
	public int deleteWorkSpaceAllMember(int workspace_seq);
	// 6. 전체 워크스페이스 리스트 출력
	public List<WorkSpaceDto> selectAllWorkSpace();
	// 7. 해당 회원이 등록되어있는 워크스페이스 리스트 출력
	public List<WorkSpaceDto> selectMemberWorkSpace(String member_id);
	// 8. 1개 워크스페이스 출력
	public WorkSpaceDto selectOneWorkSpace(WorkSpaceDto wsDto);
	// 9. 가장 최근에 생성된 워크스페이스의 번호
	public int getLastWorkSpaceSeq();
	// 10. 해당 워크스페이스의 맴버 리스트 호출 (본인 제외)
	public List<WorkSpaceMemberDto> callWorkspaceMemberList(WorkSpaceMemberDto wsmemDto);
	// 11. 워크스페이스 초대 맴버 리스트
	public List<MemberDto> callWorkspaceInviteList(int workspace_seq);
	// 12. 체크한 맴버들 워크스페이스로 초대
	public int inviteWorkspace(WorkSpaceMemberDto dto);
	// 13. 체크한 맴버들 워크프세이스에서 추방
	public int banishWorkspace(WorkSpaceMemberDto dto);

	// Channel CRUD
	// 1. 채널 추가
	public int createChannel(ChannelDto dto);
	// 2. 채널 정보 수정
	public int channelUpdate(ChannelDto dto);
	// 3. 채널 삭제
	public int channelDelete(int channel_num);
	// 4-1. 아이디가 관리자일 경우 전체 채널 출력
	public List<ChannelDto> channelAdminList();
	// 4-2. 해당 아이디가 가지고 있는 채널 출력
	public List<ChannelDto> channelList(ChannelDto chDto);
	// 5. 1개의 채널 정보만 출력
	public ChannelDto channelSelect(int channel_num);
	// 6. 채널참여자 리스트에 인서트
	public int channelMemberAdd(ChannelMemberDto chmemDto);
	// 7. 해당 채널의 참여자 출력
	public List<MemberDto> channelMemeberList(String channel_name);
	// 8. 전체 회원 조회
	public List<String> allMemeberList();
	// 9. 해당 채널에 없는 회원 조회
	public List<ChannelMemberDto> otherMemeberList(String channel_name);
	// 10. 해당 채널의 참여 회원 삭제
	public int delChannelMember(ChannelMemberDto dto);
	// 11. 채널 수정or삭제 권한 유효성 검사
	public String delIdCheck(int channel_num);	
	// 12. 가장 최근에 생성된 채널의 번호
	public int getLastChannelSeq();
	
	
	// Message CRUD
	// 1. 메세지 초대 리스트 불러오기
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto);
	// 2. 메세지 룸 생성
	public int createMessageRoom(MessageRoomDto dto);
	
}
