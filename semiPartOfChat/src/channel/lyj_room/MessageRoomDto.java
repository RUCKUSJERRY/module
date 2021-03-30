package channel.lyj_room;

import java.util.Date;

public class MessageRoomDto {

	private int messageroom_seq;
	private int workspace_seq;
	private String member_id_one;
	private String member_name_one;
	private String member_id_two;
	private String member_name_two;
	private Date messageroom_regdate;	
	
	public MessageRoomDto() {
		
	}

	public MessageRoomDto(int messageroom_seq, int workspace_seq, String member_id_one, String member_name_one,
			String member_id_two, String member_name_two, Date messageroom_regdate) {
		this.messageroom_seq = messageroom_seq;
		this.workspace_seq = workspace_seq;
		this.member_id_one = member_id_one;
		this.member_name_one = member_name_one;
		this.member_id_two = member_id_two;
		this.member_name_two = member_name_two;
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

	public String getMember_id_one() {
		return member_id_one;
	}

	public void setMember_id_one(String member_id_one) {
		this.member_id_one = member_id_one;
	}

	public String getMember_name_one() {
		return member_name_one;
	}

	public void setMember_name_one(String member_name_one) {
		this.member_name_one = member_name_one;
	}

	public String getMember_id_two() {
		return member_id_two;
	}

	public void setMember_id_two(String member_id_two) {
		this.member_id_two = member_id_two;
	}

	public String getMember_name_two() {
		return member_name_two;
	}

	public void setMember_name_two(String member_name_two) {
		this.member_name_two = member_name_two;
	}

	public Date getMessageroom_regdate() {
		return messageroom_regdate;
	}

	public void setMessageroom_regdate(Date messageroom_regdate) {
		this.messageroom_regdate = messageroom_regdate;
	}
	
	

	
	
}






