package channel.channel;

import java.util.Date;

// 해당 채널의 맴버들의 정보를 전달해주는 DTO

public class ChannelMemberDto {

	private int channelmember_num;
	private int channel_num;
	private int member_num;
	private Date regdate;
	
	public ChannelMemberDto() {
		
	}

	public ChannelMemberDto(int channelmember_num, int channel_num, int member_num, Date regdate) {
		this.channelmember_num = channelmember_num;
		this.channel_num = channel_num;
		this.member_num = member_num;
		this.regdate = regdate;
	}

	public int getChannelmember_num() {
		return channelmember_num;
	}

	public void setChannelmember_num(int channelmember_num) {
		this.channelmember_num = channelmember_num;
	}

	public int getChannel_num() {
		return channel_num;
	}

	public void setChannel_num(int channel_num) {
		this.channel_num = channel_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
}