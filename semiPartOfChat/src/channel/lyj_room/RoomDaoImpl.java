package channel.lyj_room;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db_lyj.SqlMapConfig;
import channel.lyj_member.MemberDto;

public class RoomDaoImpl extends SqlMapConfig implements RoomDao {

	@Override
	public int createRoom(RoomDto dto) {
		return 0;
	}

	@Override
	public int channelUpdate(RoomDto dto) {
		return 0;
	}

	@Override
	public int channelDelete() {
		return 0;
	}

	@Override
	public List<RoomDto> channelAdminList() {
		SqlSession session = null;
		List<RoomDto> list = new ArrayList<RoomDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-room.channelAdminList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public List<RoomDto> channelList(String member_id) {
		SqlSession session = null;
		List<RoomDto> list = new ArrayList<RoomDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper.channelList", member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public RoomDto channelSelect(int channel_num) {
		return null;
	}

	@Override
	public int roomMemberAdd(RoomMemberDto roomDto) {
		return 0;
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
	public List<RoomMemberDto> otherMemeberList(String channel_name) {
		return null;
	}

	@Override
	public int delChannelMember(RoomMemberDto dto) {
		return 0;
	}

}
