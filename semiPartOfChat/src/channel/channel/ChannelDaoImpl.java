package channel.channel;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import channel.channel.db.SqlMapConfig;
import channel.member.dto.MemberDto;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

public class ChannelDaoImpl extends SqlMapConfig implements ChannelDao {

	
	
	
	
	
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
