package channel.lyj_room;

import java.util.Date;

public class WorkSpaceDto {

	private int workspace_seq;
	private String workspace_name;
	private String workspace_information;
	private String member_id;
	private Date workspace_regdate;
	
	public WorkSpaceDto() {
		
	}

	public WorkSpaceDto(int workspace_seq, String workspace_name, String workspace_information, String member_id,
			Date workspace_regdate) {
		this.workspace_seq = workspace_seq;
		this.workspace_name = workspace_name;
		this.workspace_information = workspace_information;
		this.member_id = member_id;
		this.workspace_regdate = workspace_regdate;
	}

	public int getWorkspace_seq() {
		return workspace_seq;
	}

	public void setWorkspace_seq(int workspace_seq) {
		this.workspace_seq = workspace_seq;
	}

	public String getWorkspace_name() {
		return workspace_name;
	}

	public void setWorkspace_name(String workspace_name) {
		this.workspace_name = workspace_name;
	}

	public String getWorkspace_information() {
		return workspace_information;
	}

	public void setWorkspace_information(String workspace_information) {
		this.workspace_information = workspace_information;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getWorkspace_regdate() {
		return workspace_regdate;
	}

	public void setWorkspace_regdate(Date workspace_regdate) {
		this.workspace_regdate = workspace_regdate;
	}
	
	

	
	
}

