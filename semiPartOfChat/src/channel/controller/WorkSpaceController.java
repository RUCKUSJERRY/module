package channel.controller;

import java.io.IOException;
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

import channel.channel.ChannelBiz;
import channel.channel.ChannelBizImpl;
import channel.channel.ChannelDto;
import channel.channel.ChannelMemberDto;
import channel.workspace.WorkSpaceBiz;
import channel.workspace.WorkSpaceBizImpl;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

@WebServlet("/WorkSpaceController")
public class WorkSpaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WorkSpaceController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 워크스페이스 출력,생성,수정,삭제 관련 서블릿
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		WorkSpaceBiz biz = new WorkSpaceBizImpl();
		ChannelBiz chBiz = new ChannelBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		if (command.equals("selectMemberWorkSpace")) {
			// 로그인 한 회원의 워크스페이스 리스트 불러오기
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			System.out.println(member_num);
			List<WorkSpaceDto> list = biz.selectMemberWorkSpace(member_num);
			JsonArray resultArray = new JsonArray();
			Gson gson = new Gson();
			
			String jsonString = gson.toJson(list);
			resultArray.add(JsonParser.parseString(jsonString));

			JsonObject result = new JsonObject();
			result.add("result", resultArray);

			response.getWriter().append(result + "");
			System.out.println(resultArray);

		}else if (command.equals("addWorkSpace")) {
			// 워크스페이스 추가시
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String workspace_name = request.getParameter("workspace_name");
			String workspace_information = request.getParameter("workspace_information");
			
			WorkSpaceDto wsDto = new WorkSpaceDto();
			wsDto.setMember_num(member_num);
			wsDto.setWorkspace_name(workspace_name);
			wsDto.setWorkspace_information(workspace_information);
			
			int wsres = biz.createWorkSpace(wsDto);
			
				if (wsres > 0) {
					System.out.println("워크스페이스 생성 중...");
					//방금 생성한 워크스페이스의 번호 가져오기?..
					int workspace_num = biz.getLastWorkSpaceSeq();
					
					if (workspace_num > 0) {
						System.out.println("워크스페이스 번호 : " + workspace_num);
						// 워크스페이스의 맴버로 추가하기
						WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
						wsmemDto.setMember_num(member_num);
						wsmemDto.setWorkspace_name(workspace_name);
						wsmemDto.setWorkspace_num(workspace_num);
						
						int wsmemRes = biz.insertWorkSpaceMember(wsmemDto);
						
						if (wsmemRes > 0) {
							System.out.println(workspace_num + "번 워크스페이스 맴버로 추가 완료");
							// 워크스페이스의 전체채팅방 만들기
							ChannelDto chDto = new ChannelDto();
							chDto.setWorkspace_num(workspace_num);
							chDto.setMember_num(member_num);
							chDto.setChannel_name("전체");
							chDto.setChannel_information(workspace_name+" 워크스페이스의 전체채팅방입니다. 삭제 불가합니다.");
							chDto.setChannel_access("PUBLIC");
							chDto.setChannel_enabled("Y");
							int chRes = chBiz.addChannel(chDto);
							
							if (chRes > 0) {
								System.out.println(workspace_num + "번 워크스페이스 전체 채팅방 생성");
								// 방금 생성한 채널의 번호 가져오기?..
								int channel_num = chBiz.getLastChannelSeq();
								if (channel_num > 0) {
									System.out.println(workspace_num + "번 워크스페이스 전채채팅방 번호 : " + channel_num);
									
									// 워크스페이스의 전체채팅방 맴버로 넣기
									ChannelMemberDto chmemDto = new ChannelMemberDto();
									chmemDto.setMember_num(member_num);
									chmemDto.setChannel_num(channel_num);
									int chmemRes = chBiz.addChannelMember(chmemDto);
									
									if (chmemRes > 0) {
										System.out.println(workspace_num + "번 워크스페이스의 전채채팅방의 맴버 추가 완료" );
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
		} else if (command.equals("delWorkSpace")) {
				// 워크스페이스 삭제 요청
				int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
				System.out.println(workspace_num);
				int res = biz.deleteWorkSpace(workspace_num);
		
				if (res > 0) {
					System.out.println("워크스페이스 삭제 성공");
					dispatch("workspace.jsp", request, response);
					
				} else {
					System.out.println("워크스페이스 삭제 실패");
					response.sendRedirect("workspace.jsp");	
				}
			
		} else if (command.equals("selectWorkspaceMemberList")) {
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			System.out.println(workspace_num + " : " + member_num);
			
			WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
			wsmemDto.setWorkspace_num(workspace_num);
			wsmemDto.setMember_num(member_num);
			
			List<WorkSpaceMemberDto> wsmemList = biz.selectWorkspaceMemberList(wsmemDto);
			
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
		}

	} 

	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
}
