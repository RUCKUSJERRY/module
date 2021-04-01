package channel.lyj_chat;

import java.util.Date;

public class MessageRoomDto {

	private int messageroom_seq;
	private int workspace_seq;
	private String member_id;
	private String member_name;
	private String member2_id;
	private String member2_name;
	private Date messageroom_regdate;
	
	public MessageRoomDto() {
		
	}

	public MessageRoomDto(int messageroom_seq, int workspace_seq, String member_id, String member_name,
			String member2_id, String member2_name, Date messageroom_regdate) {
		this.messageroom_seq = messageroom_seq;
		this.workspace_seq = workspace_seq;
		this.member_id = member_id;
		this.member_name = member_name;
		this.member2_id = member2_id;
		this.member2_name = member2_name;
		this.messageroom_regdate = messageroom_regdate;
	}

	public int getMessageroom_seq() {
		return messageroom_seq;
	}

	public void setMessageroom_seq(int messageroom_seq) {
		this.messageroom_seq = messageroom_seq;
	}

	public int getWorkspace_seq() {
		return workspace_seq;
	}

	public void setWorkspace_seq(int workspace_seq) {
		this.workspace_seq = workspace_seq;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember2_id() {
		return member2_id;
	}

	public void setMember2_id(String member2_id) {
		this.member2_id = member2_id;
	}

	public String getMember2_name() {
		return member2_name;
	}

	public void setMember2_name(String member2_name) {
		this.member2_name = member2_name;
	}

	public Date getMessageroom_regdate() {
		return messageroom_regdate;
	}

	public void setMessageroom_regdate(Date messageroom_regdate) {
		this.messageroom_regdate = messageroom_regdate;
	}
	
	
	
	
}
