package channel.workspace;

import java.util.List;

import channel.member.dto.MemberDto;

public class WorkSpaceBizImpl implements WorkSpaceBiz {

	WorkSpaceDao dao = new WorkSpaceDaoImpl();
	
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
		int res = dao.deleteWorkSpace(workspace_seq);
		return res;
	}

	// 6. 전체 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectAllWorkSpace() {
		
		return null;
	}

	// 7. 해당 회원이 등록되어있는 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectMemberWorkSpace(int member_num) {
		return dao.selectMemberWorkSpace(member_num);
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
	
}
