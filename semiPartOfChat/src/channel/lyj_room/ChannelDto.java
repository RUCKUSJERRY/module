package channel.lyj_room;

import java.util.Date;

// 채널의 정보를 전달해주는 DTO

public class ChannelDto {

	private int channel_seq;
	private int workspace_seq;
	private String channel_name;
	private String channel_information;
	private String member_id;
	private String channel_access;
	private String channel_enabled;
	private Date channel_regdate;
	
	public ChannelDto() {
		
	}

	public ChannelDto(int channel_seq, int workspace_seq, String channel_name, String channel_information,
			String member_id, String channel_access, String channel_enabled, Date channel_regdate) {
		this.channel_seq = channel_seq;
		this.workspace_seq = workspace_seq;
		this.channel_name = channel_name;
		this.channel_information = channel_information;
		this.member_id = member_id;
		this.channel_access = channel_access;
		this.channel_enabled = channel_enabled;
		this.channel_regdate = channel_regdate;
	}

	public int getChannel_seq() {
		return channel_seq;
	}

	public void setChannel_seq(int channel_seq) {
		this.channel_seq = channel_seq;
	}

	public int getWorkspace_seq() {
		return workspace_seq;
	}

	public void setWorkspace_seq(int workspace_seq) {
		this.workspace_seq = workspace_seq;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_information() {
		return channel_information;
	}

	public void setChannel_information(String channel_information) {
		this.channel_information = channel_information;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getChannel_access() {
		return channel_access;
	}

	public void setChannel_access(String channel_access) {
		this.channel_access = channel_access;
	}

	public String getChannel_enabled() {
		return channel_enabled;
	}

	public void setChannel_enabled(String channel_enabled) {
		this.channel_enabled = channel_enabled;
	}

	public Date getChannel_regdate() {
		return channel_regdate;
	}

	public void setChannel_regdate(Date channel_regdate) {
		this.channel_regdate = channel_regdate;
	}
	
	
	
	
	
}