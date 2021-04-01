package channel.controller_lyj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import channel.lyj_chat.ChatBiz;
import channel.lyj_chat.ChatBizImpl;
import channel.lyj_common.Util;
import channel.lyj_room.ChannelBiz;
import channel.lyj_room.ChannelBizImpl;
import channel.lyj_room.ChannelDto;
import channel.lyj_room.ChannelMemberDto;
import channel.lyj_room.MessageRoomDto;
import channel.lyj_room.WorkSpaceDto;
import channel.lyj_room.WorkSpaceMemberDto;
import channel.member.dto.MemberDto;

@WebServlet("/ChannelController")
public class ChannelController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChannelController_lyj() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 워크스페이스, 채널채팅방, 메세지창 출력,생성,수정,삭제 관련 서블릿
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ChannelBiz chBiz = new ChannelBizImpl();
		ChatBiz msgBiz = new ChatBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		// 워크스페이스로 이동
		if (command.equals("WorkSpace")) {
			response.sendRedirect("workspace.jsp");
		
		} else if (command.equals("WorkSpaceAdd")) {
			// 워크스페이스 추가시	
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			String workspace_name = request.getParameter("workspace_name");
			String workspace_information = request.getParameter("workspace_information");
			
			WorkSpaceDto wsDto = new WorkSpaceDto();
			wsDto.setMember_id(member_id);
			wsDto.setWorkspace_name(workspace_name);
			wsDto.setWorkspace_information(workspace_information);
			
			int wsRes = chBiz.createWorkSpace(wsDto);
			
				if (wsRes > 0) {
					System.out.println("워크스페이스 생성 중...");
					//방금 생성한 워크스페이스의 번호 가져오기?..
					int workspace_seq = chBiz.getLastWorkSpaceSeq();
					
					if (workspace_seq > 0) {
						System.out.println("워크스페이스 번호 : " + workspace_seq);
						// 워크스페이스의 맴버로 추가하기
						WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
						wsmemDto.setMember_id(member_id);
						wsmemDto.setMember_name(member_name);
						wsmemDto.setWorkspace_name(workspace_name);
						wsmemDto.setWorkspace_seq(workspace_seq);
						
						int wsmemRes = chBiz.insertWorkSpaceMember(wsmemDto);
						
						if (wsmemRes > 0) {
							System.out.println(workspace_seq + "번 워크스페이스 맴버로 추가 완료");
							// 워크스페이스의 전체채팅방 만들기
							ChannelDto chDto = new ChannelDto();
							chDto.setWorkspace_seq(workspace_seq);
							chDto.setChannel_name("전체");
							chDto.setChannel_information("해당 채널의 전체채팅방입니다. 삭제 불가합니다.");
							chDto.setMember_id("ADMIN");
							chDto.setChannel_access("PUBLIC");
							chDto.setChannel_enabled("Y");
							int chRes = chBiz.createChannel(chDto);
							
							if (chRes > 0) {
								System.out.println(workspace_seq + "번 워크스페이스 전체 채팅방 생성");
								// 방금 생성한 채널의 번호 가져오기?..
								int channel_seq = chBiz.getLastChannelSeq();
								if (channel_seq > 0) {
									System.out.println(workspace_seq + "번 워크스페이스 전채채팅방 번호 : " + channel_seq);
									
									// 워크스페이스의 전체채팅방 맴버로 넣기
									ChannelMemberDto chmemDto = new ChannelMemberDto();
									chmemDto.setChannel_seq(channel_seq);
									chmemDto.setMember_id(member_id);
									chmemDto.setMember_name(member_name);
									
									int chmemRes = chBiz.channelMemberAdd(chmemDto);
									
									if (chmemRes > 0) {
										System.out.println(workspace_seq + "번 워크스페이스의 전채채팅방의 맴버 추가 완료" );
										dispatch("workspace.jsp", request, response);
									} else {
										System.out.println("ERROR! 전체채팅방 맴버 추가 실패, 관리자에게 문의 바랍니다.");
										response.sendRedirect("workspace.jsp");
									}									
								} else {
									System.out.println("ERROR! 전체채팅방 번호 호출 실패, 관리자에게 문의 바랍니다.");
									response.sendRedirect("workspace.jsp");
								}								
							} else {
								System.out.println("ERROR! 워크스페이스의 전체채팅방이 추가 되지 않았습니다. 관리자에게 문의 바랍니다.");
								response.sendRedirect("workspace.jsp");
							}						
						} else {
							System.out.println("ERROR! 워크스페이스의 맴버로 추가 되지 않았습니다. 관리자에게 문의 바랍니다.");
							response.sendRedirect("workspace.jsp");
						}				
					} else {
						System.out.println("ERROR! 워크스페이스 번호 호출 실패, 관리자에게 문의 바랍니다.");
						response.sendRedirect("workspace.jsp");
					}		
				} else {
					System.out.println("ERROR! 워크스페이스 추가 실패");
					response.sendRedirect("workspace.jsp");
				}
		} else if (command.equals("WorkSpaceDel")) {
			// 워크스페이스 삭제 요청
			int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
			System.out.println(workspace_seq);
			int res = chBiz.deleteWorkSpace(workspace_seq);
	
			if (res > 0) {
				System.out.println("워크스페이스 삭제 성공");
				dispatch("workspace.jsp", request, response);
				
			} else {
				System.out.println("워크스페이스 삭제 실패");
				response.sendRedirect("workspace.jsp");	
			}
		
		} else if (command.equals("selectMemberWorkSpace")) {
		 // 로그인 한 회원의 워크스페이스 리스트 불러오기
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			List<WorkSpaceDto> list = chBiz.selectMemberWorkSpace(member_id);
			JsonArray resultArray = new JsonArray();
			Gson gson = new Gson();

			String jsonString = gson.toJson(list);
			resultArray.add(JsonParser.parseString(jsonString));

			JsonObject result = new JsonObject();
			result.add("result", resultArray);

			response.getWriter().append(result + "");
			System.out.println(resultArray);
			
		} else if (command.equals("channelAdminList")) {
		// 로그인시 어드민으로 로그인 할 경우
		
		int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
		List<ChannelDto> list = chBiz.channelAdminList();
		
			if (list != null) {
				request.setAttribute("channelList", list);
				dispatch("admin.jsp", request, response);
			}

		// 로그인시 일반 유저로 로그인 할 경우
		} else if (command.equals("channelList")) {
			
			String member_id = request.getParameter("member_id");
			int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
			
			ChannelDto chDto = new ChannelDto();
			chDto.setWorkspace_seq(workspace_seq);
			chDto.setMember_id(member_id);
			
			MessageRoomDto msgDto = new MessageRoomDto();
			msgDto.setMember_id(member_id);
			msgDto.setWorkspace_seq(workspace_seq);
			
			List<ChannelDto> chList = chBiz.channelList(chDto);
			List<MessageRoomDto> msgRoomList = msgBiz.messageRoomList(msgDto);
			
				request.setAttribute("channelList", chList);
				request.setAttribute("messageRoomList", msgRoomList);
				dispatch("main.jsp", request, response);
			
		// 1개 채널 SELECT
		} else if (command.equals("channelSelect")) {
			
			int channel_seq = Integer.parseInt(request.getParameter("channel_seq"));

			ChannelDto dto = chBiz.channelSelect(channel_seq);
			
			Util util = new Util();
			
			String result = "";
			result += dto.getChannel_seq() + "|\\|";
			result += dto.getChannel_information() + "|\\|";
			result += dto.getChannel_enabled() + "|\\|";
			result += util.getTostrings(dto.getChannel_regdate());

			System.out.println(result);

			response.getWriter().append(result);
			
		
		} else if (command.equals("channelAdd")) {
			// 채널 추가시	
			int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			String channel_access = request.getParameter("channel_access");
			
			ChannelDto chDto = new ChannelDto();
			chDto.setWorkspace_seq(workspace_seq);
			chDto.setChannel_name(channel_name);
			chDto.setChannel_information(channel_information);
			chDto.setMember_id(member_id);
			chDto.setChannel_access(channel_access);
			chDto.setChannel_enabled("Y");
			
			int res = chBiz.createChannel(chDto);
			
			if (res > 0) {
				System.out.println(workspace_seq + "번 워크스페이스의 "+channel_name+"채널 생성 완료");
				
				int channel_seq = chBiz.getLastChannelSeq();
				
				ChannelMemberDto chmemDto = new ChannelMemberDto();
				chmemDto.setChannel_seq(channel_seq);
				chmemDto.setMember_id(member_id);
				chmemDto.setMember_name(member_name);

				int resAdd = chBiz.channelMemberAdd(chmemDto);
				
				if (resAdd > 0) {
					System.out.println(channel_name+"채널의 맴버로 추가 완료");
					dispatch("ChannelController?command=channelList&member_id="+member_id+"&workspace_seq="+workspace_seq, request, response);
				} else {
					System.out.println("ERROR! 채널의 맴버로 추가되는 데에 실패하였습니다. 관리자에게 문의하세요!");
					response.sendRedirect("ChannelController?command=channelList&member_id="+member_id+"&workspace_seq="+workspace_seq);
				}
			} else {
				System.out.println("ERROR! 채널 추가에 실패하였습니다. 관리자에게 문의하세요!");
				response.sendRedirect("ChannelController?command=channelList&member_id"+member_id+"&workspace_seq="+workspace_seq);
			}		
		} else if (command.equals("channelDelete")) {
			//채널 삭제시
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			int channel_seq = Integer.parseInt(request.getParameter("channel_seq"));
			String msg = "";
			// 유효성 체크
			String delIdCheck = chBiz.delIdCheck(channel_seq);
			System.out.println(delIdCheck);
			if (delIdCheck.equals(member_id)) {
				int res = chBiz.channelDelete(channel_seq);
				 
				if (res > 0) {
					msg = "채널을 삭제하였습니다.";
					response.getWriter().append(msg);
				} else {
					msg = "채널을 삭제에 실패하였습니다.";
					response.getWriter().append(msg);
				}
				
			} else {
				msg = "채널을 삭제할 권한이 없습니다.";
				response.getWriter().append(msg);	
			}	
		} else if (command.equals("channelUpdate")) {
			int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
			int channel_seq = Integer.parseInt(request.getParameter("channel_seq"));
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String channel_access = request.getParameter("channel_access");
			String member_id = request.getParameter("member_id");
				
				ChannelDto dto = new ChannelDto();
				dto.setChannel_seq(channel_seq);
				dto.setChannel_name(channel_name);
				dto.setChannel_information(channel_information);
				dto.setChannel_access(channel_access);
				dto.setMember_id(member_id);
				
				int res = chBiz.channelUpdate(dto);
				 
				if (res > 0) {
					System.out.println("채널 정보 수정 성공");
					dispatch("ChannelController?command=channelList&member_id="+member_id+"&workspace_seq="+workspace_seq, request, response);
				} else {
					System.out.println("채널 정보 수정 실패 : 권한이 있는지 관리자에게 문의바랍니다.");
					dispatch("ChannelController?command=channelList&member_id="+member_id+"&workspace_seq="+workspace_seq, request, response);
				}
					
			} else if (command.equals("callWorkspaceMemberList")) {
				int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
				String member_id = request.getParameter("member_id");
				
				System.out.println(workspace_seq + " : " + member_id);
				
				WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
				wsmemDto.setWorkspace_seq(workspace_seq);
				wsmemDto.setMember_id(member_id);
				
				List<WorkSpaceMemberDto> wsmemList = chBiz.callWorkspaceMemberList(wsmemDto);
				
				if (wsmemList != null) {
					JsonArray resultArray = new JsonArray();
					Gson gson = new Gson();
					String jsonString = gson.toJson(wsmemList);
					resultArray.add(JsonParser.parseString(jsonString));
					JsonObject result = new JsonObject();
					result.add("result", resultArray);
					
					response.getWriter().append(result + "");
					System.out.println(resultArray);
				} else {
					response.getWriter().append("맴버가 없습니다.");
				}
				
			// 워크스페이스 초대 맴버 리스트	
			} else if (command.equals("callWorkspaceInviteList")) {
				
				int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));

				List<MemberDto> list = chBiz.callWorkspaceInviteList(workspace_seq);
				
				if (list != null) {
					JsonArray resultArray = new JsonArray();
					Gson gson = new Gson();
					String jsonString = gson.toJson(list);
					resultArray.add(JsonParser.parseString(jsonString));
					JsonObject result = new JsonObject();
					result.add("result", resultArray);
					
					response.getWriter().append(result + "");
					System.out.println(resultArray);
				} else {
					response.getWriter().append("초대할 맴버가 없습니다.");
				}
			} else if (command.equals("inviteWorkspace")) {
				int count = Integer.parseInt(request.getParameter("count"));
				String [] inviteMember = request.getParameterValues("inviteMember");

				List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
				 
				for (int i = 0; i < count; i++) {
					String [] member = inviteMember[i].split(",");
					WorkSpaceMemberDto dto = new WorkSpaceMemberDto();
					dto.setWorkspace_seq(Integer.parseInt(member[0]));
					dto.setWorkspace_name(member[1]);
					dto.setMember_id(member[2]);
					dto.setMember_name(member[3]);
					
					list.add(dto);
				}
				
				int res = 0;
				
				for (WorkSpaceMemberDto dto : list) {
					res = chBiz.inviteWorkspace(dto);
				}

				if (res > 0) {
					System.out.println("워크스페이스 맴버 추가 성공");
					response.getWriter().append("워크스페이스 맴버 추가 성공");
				} else {
					System.out.println("워크스페이스 맴버 추가 실패");
					response.getWriter().append("워크스페이스 맴버 추가 실패");
				}
				
			} else if (command.equals("banishWorkspace")){
				int count = Integer.parseInt(request.getParameter("count"));
				String [] banishMember = request.getParameterValues("banishMember");
				
				List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
				 
				for (int i = 0; i < count; i++) {
					String [] member = banishMember[i].split(",");
					WorkSpaceMemberDto dto = new WorkSpaceMemberDto();
					dto.setWorkspace_seq(Integer.parseInt(member[0]));
					dto.setWorkspace_name(member[1]);
					dto.setMember_id(member[2]);
					dto.setMember_name(member[3]);
					
					list.add(dto);
				}
				
				int res = 0;
				
				for (WorkSpaceMemberDto dto : list) {
					res = chBiz.banishWorkspace(dto);
				}

				if (res > 0) {
					System.out.println("워크스페이스 맴버 삭제 성공");
					response.getWriter().append("워크스페이스 맴버 삭제 성공");
				} else {
					System.out.println("워크스페이스 맴버 삭제 실패");
					response.getWriter().append("워크스페이스 맴버 삭제 실패");
				}
				
			} else if (command.equals("callInviteMessageMemberList")) {
				int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
				String member_id = request.getParameter("member_id");
				String member_name = request.getParameter("member_name");
				
				WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
				wsmemDto.setWorkspace_seq(workspace_seq);
				wsmemDto.setMember_id(member_id);
				
				List<WorkSpaceMemberDto> list = chBiz.callInviteMessageMemberList(wsmemDto);
				
				if (list != null) {
					JsonArray resultArray = new JsonArray();
					Gson gson = new Gson();
					String jsonString = gson.toJson(list);
					resultArray.add(JsonParser.parseString(jsonString));
					JsonObject result = new JsonObject();
					result.add("result", resultArray);
					
					response.getWriter().append(result + "");
					System.out.println(resultArray);
				} else {
					response.getWriter().append("초대할 맴버가 없습니다.");
				}

			} else if (command.equals("createMessageRoom")) {
				int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
				String member_id = request.getParameter("member_id");
				String member_name = request.getParameter("member_name");
				String member2_id = request.getParameter("member2_id");
				String member2_name = request.getParameter("member2_name");
				
				MessageRoomDto dto = new MessageRoomDto();
				dto.setWorkspace_seq(workspace_seq);
				dto.setMember_id(member_id);
				dto.setMember_name(member_name);
				dto.setMember2_id(member2_id);
				dto.setMember2_name(member2_name);
				
				int res = chBiz.createMessageRoom(dto);
				
				if (res > 0) {
					System.out.println("메세지룸 생성 성공");
					response.getWriter().append("메세지룸 생성 성공");
				} else {
					System.out.println("메세지룸 생성 실패");
					response.getWriter().append("메세지룸 생성 실패");
				}
				
			}
			
		}

	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
