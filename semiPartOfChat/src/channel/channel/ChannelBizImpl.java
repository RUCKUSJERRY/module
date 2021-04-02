package channel.channel;

import java.util.List;

import channel.member.dto.MemberDto;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

public class ChannelBizImpl implements ChannelBiz {

	ChannelDao dao = new ChannelDaoImpl();

	
	
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
	
	// 13. 해당 채널의 맴버 리스트 호출
	@Override
	public List<ChannelMemberDto> callChannelMemberList(ChannelMemberDto dto) {
		return dao.callChannelMemberList(dto);
	}

	// 14. 해당 채널의 초대 가능한 맴버 리스트 호출
	@Override
	public List<WorkSpaceMemberDto> callChannelInviteList(ChannelDto dto) {
		return dao.callChannelInviteList(dto);
	}
	
	// Message CRUD
	// 1. 메세지 초대 리스트 불러오기
	@Override
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto) {
		return dao.callInviteMessageMemberList(wsmemDto);
	}
	
	
}
