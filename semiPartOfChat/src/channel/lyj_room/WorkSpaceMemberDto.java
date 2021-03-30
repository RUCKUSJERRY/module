package channel.lyj_room;

import java.util.Date;

public class WorkSpaceMemberDto {

	private int workspacemember_seq;
	private int workspace_seq;
	private String workspace_name;
	private String member_id;
	private String member_name;
	private Date workspacemember_regdate;
	
	public WorkSpaceMemberDto() {
		
	}

	public WorkSpaceMemberDto(int workspacemember_seq, int workspace_seq, String workspace_name, String member_id,
			String member_name, Date workspacemember_regdate) {
		this.workspacemember_seq = workspacemember_seq;
		this.workspace_seq = workspace_seq;
		this.workspace_name = workspace_name;
		this.member_id = member_id;
		this.member_name = member_name;
		this.workspacemember_regdate = workspacemember_regdate;
	}

	public int getWorkspacemember_seq() {
		return workspacemember_seq;
	}

	public void setWorkspacemember_seq(int workspacemember_seq) {
		this.workspacemember_seq = workspacemember_seq;
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

	public Date getWorkspacemember_regdate() {
		return workspacemember_regdate;
	}

	public void setWorkspacemember_regdate(Date workspacemember_regdate) {
		this.workspacemember_regdate = workspacemember_regdate;
	}

	
	
}

