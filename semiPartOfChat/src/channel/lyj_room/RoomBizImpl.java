package channel.lyj_room;

import java.util.List;

import channel.lyj_member.MemberDto;

public class RoomBizImpl implements RoomBiz {

	RoomDao dao = new RoomDaoImpl();
	
	@Override
	public int createRoom(RoomDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int channelUpdate(RoomDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int channelDelete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoomDto> channelAdminList() {
		return dao.channelAdminList();
	}

	@Override
	public List<RoomDto> channelList(String member_id) {
		return dao.channelList(member_id);
	}

	@Override
	public RoomDto channelSelect(int channel_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int roomMemberAdd(RoomMemberDto roomDto) {
		// TODO Auto-generated method stub
		return 0;
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
	public List<RoomMemberDto> otherMemeberList(String channel_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delChannelMember(RoomMemberDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
