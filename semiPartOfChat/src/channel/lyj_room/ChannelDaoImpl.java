package channel.lyj_room;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db_lyj.SqlMapConfig;
import channel.member.dto.MemberDto;

public class ChannelDaoImpl extends SqlMapConfig implements ChannelDao {

	// WorkSpace CRUD
	
	// 1. 워크스페이스 생성
	@Override
	public int createWorkSpace(WorkSpaceDto wsDto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.createWorkSpace", wsDto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	// 2. 워크스페이스 맴버 추가
	@Override
	public int insertWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.insertWorkSpaceMember", wsmemDto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	// 3. 워크스페이스 맴버 제거
	@Override
	public int deleteWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 4. 워크스페이스 정보 수정
	@Override
	public int updateWorkSpace(WorkSpaceDto wsDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 5. 워크스페이스 삭제
	@Override
	public int deleteWorkSpace(int workspace_seq) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-channel.deleteWorkSpace", workspace_seq);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	// 5-1. 삭제하는 워크스페이스의 맴버 전원 삭제
	public int deleteWorkSpaceAllMember(int workspace_seq) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-channel.deleteWorkSpaceAllMember", workspace_seq);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	// 6. 전체 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectAllWorkSpace() {
		// TODO Auto-generated method stub
		return null;
	}

	// 7. 해당 회원이 등록되어있는 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectMemberWorkSpace(String member_id) {
		SqlSession session = null;
		List<WorkSpaceDto> list = new ArrayList<WorkSpaceDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.selectMemberWorkSpace", member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	// 8. 1개 워크스페이스 출력
	@Override
	public WorkSpaceDto selectOneWorkSpace(WorkSpaceDto wsDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 9. 가장 최근에 생성된 워크스페이스의 번호
	@Override
	public int getLastWorkSpaceSeq() {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-channel.getLastWorkSpaceSeq");
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;

	}
	
	// 10. 해당 워크스페이스의 맴버 리스트 호출 (본인 제외)
	@Override
	public List<WorkSpaceMemberDto> callWorkspaceMemberList(WorkSpaceMemberDto wsmemDto) {
		SqlSession session = null;
		List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.callWorkspaceMemberList", wsmemDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
			return list;
	}
	
	// 11. 워크스페이스 초대 맴버 리스트
	@Override
	public List<MemberDto> callWorkspaceInviteList(int workspace_seq) {
		SqlSession session = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.callWorkspaceInviteList", workspace_seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
			return list;
		
	}
	
	// 12. 체크한 맴버들 워크스페이스로 초대
	@Override
	public int inviteWorkspace(WorkSpaceMemberDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.inviteWorkspace", dto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	// 13. 체크한 맴버들 워크프세이스에서 추방
	@Override
	public int banishWorkspace(WorkSpaceMemberDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-channel.banishWorkspace", dto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	
	
	
	
	@Override
	public int createChannel(ChannelDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.createChannel", dto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int channelUpdate(ChannelDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("channelmapper-channel.channelUpdate", dto);
			if (res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public int channelDelete(int channel_num) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-channel.channelDelete", channel_num);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public List<ChannelDto> channelAdminList() {
		SqlSession session = null;
		List<ChannelDto> list = new ArrayList<ChannelDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.channelAdminList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public List<ChannelDto> channelList(ChannelDto chDto) {
		SqlSession session = null;
		List<ChannelDto> list = new ArrayList<ChannelDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.channelList", chDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public ChannelDto channelSelect(int channel_num) {
		SqlSession session = null;
		ChannelDto dto = new ChannelDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne("channelmapper-channel.channelSelect", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}
	// 6. 채널참여자 리스트에 인서트
	@Override
	public int channelMemberAdd(ChannelMemberDto chmemDto) {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.channelMemberAdd", chmemDto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	@Override
	public List<MemberDto> channelMemeberList(String channel_name) {
		return null;
	}

	@Override
	public List<String> allMemeberList() {
		return null;
	}

	@Override
	public List<ChannelMemberDto> otherMemeberList(String channel_name) {
		return null;
	}

	@Override
	public int delChannelMember(ChannelMemberDto dto) {
		return 0;
	}
	
	@Override
	// 11. 채널 수정or삭제 권한 유효성 검사
	public String delIdCheck(int channel_num) {
		SqlSession session = null;
		String res = "";
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-channel.delIdCheck", channel_num);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	// 12. 가장 최근에 생성된 채널의 번호
	@Override
	public int getLastChannelSeq() {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-channel.getLastChannelSeq");
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
		
	}
	// 13. 해당 채널의 맴버 리스트 호출
	@Override
	public List<ChannelMemberDto> callChannelMemberList(ChannelMemberDto dto) {
		List<ChannelMemberDto> list = new ArrayList<ChannelMemberDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.callChannelMemberList", dto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 
	}
	// 14. 해당 채널의 초대 가능한 맴버 리스트 호출
	@Override
	public List<WorkSpaceMemberDto> callChannelInviteList(ChannelDto dto) {
		List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.callChannelInviteList", dto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 
	}

	// Message CRUD
	// 1. 메세지 초대 리스트 불러오기
	@Override
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto) {
			List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.callInviteMessageMemberList", wsmemDto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 

	}
	
	


}
