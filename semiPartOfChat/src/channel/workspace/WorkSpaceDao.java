package channel.workspace;

import java.util.List;

import channel.member.dto.MemberDto;

public interface WorkSpaceDao {


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
	public List<WorkSpaceDto> selectMemberWorkSpace(int member_num);
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
	
}
