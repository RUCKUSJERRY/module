package channel.lyj_chat;

import java.util.Date;

public class ChatDto {

	private int chat_seq;
	private int channel_seq;
	private String member_id;
	private String member_name;
	private String chat_content;
	private Date chat_regdate;
	
	public ChatDto () {
		
	}

	public ChatDto(int chat_seq, int channel_seq, String member_id, String member_name, String chat_content,
			Date chat_regdate) {
		this.chat_seq = chat_seq;
		this.channel_seq = channel_seq;
		this.member_id = member_id;
		this.member_name = member_name;
		this.chat_content = chat_content;
		this.chat_regdate = chat_regdate;
	}

	public int getChat_seq() {
		return chat_seq;
	}

	public void setChat_seq(int chat_seq) {
		this.chat_seq = chat_seq;
	}

	public int getChannel_seq() {
		return channel_seq;
	}

	public void setChannel_seq(int channel_seq) {
		this.channel_seq = channel_seq;
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

	public String getChat_content() {
		return chat_content;
	}

	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}

	public Date getChat_regdate() {
		return chat_regdate;
	}

	public void setChat_regdate(Date chat_regdate) {
		this.chat_regdate = chat_regdate;
	}

	
	
}
