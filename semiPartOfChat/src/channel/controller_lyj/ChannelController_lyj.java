package channel.controller_lyj;

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

import channel.lyj_common.Util;
import channel.lyj_room.ChannelBiz;
import channel.lyj_room.ChannelBizImpl;
import channel.lyj_room.ChannelDto;
import channel.lyj_room.ChannelMemberDto;
import channel.lyj_room.WorkSpaceDto;
import channel.lyj_room.WorkSpaceMemberDto;

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
			
			WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
			wsmemDto.setMember_id(member_id);
			wsmemDto.setMember_name(member_name);
			wsmemDto.setWorkspace_name(workspace_name);
			
			int wsRes = chBiz.createWorkSpace(wsDto);
			
			int wsmemRes = chBiz.insertWorkSpaceMember(wsmemDto);
			
			if (wsRes > 0 && wsmemRes > 0) {
				
				response.sendRedirect("RoomController?command=channelList&member_id="+member_id);
				
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

			List<ChannelDto> list = chBiz.channelAdminList();

			if (list != null) {
				request.setAttribute("channelAdminlist", list);
				dispatch("admin.jsp", request, response);
			}

		// 로그인시 일반 유저로 로그인 할 경우
		} else if (command.equals("channelList")) {

			String member_id = request.getParameter("member_id");
			List<ChannelDto> list = chBiz.channelList(member_id);

			if (list != null) {
				request.setAttribute("channelList", list);
				dispatch("main.jsp", request, response);
			}
		
		// 1개 채널 SELECT
		} else if (command.equals("channelSelect")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));

			ChannelDto dto = chBiz.channelSelect(channel_num);
			
			Util util = new Util();
			
			String result = "";
			result += dto.getChannel_seq() + "|\\|";
			result += dto.getChannel_name() + "|\\|";
			result += dto.getChannel_information() + "|\\|";
			result += dto.getChannel_enabled() + "|\\|";
			result += util.getTostrings(dto.getChannel_regdate());

			System.out.println(result);

			response.getWriter().append(result);
			
		// 채널 추가시
		} else if (command.equals("channelAdd")) {
			
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			String channel_access = request.getParameter("channel_access");
			
			ChannelDto dto = new ChannelDto();
			dto.setChannel_name(channel_name);
			dto.setChannel_information(channel_information);
			dto.setMember_id(member_id);
			dto.setChannel_access(channel_access);
			System.out.println(channel_access);
			
			ChannelMemberDto roomDto = new ChannelMemberDto();
			roomDto.setChannel_name(channel_name);
			roomDto.setMember_id(member_id);
			roomDto.setMember_name(member_name);
			
			int res = chBiz.createRoom(dto);
			int resAdd = chBiz.roomMemberAdd(roomDto);
			
			if (res > 0 && resAdd > 0) {
				System.out.println("채널 생성 완료");
				dispatch("RoomController?command=channelList&member_id"+member_id, request, response);
			} else {
				System.out.println("채널 생성 실패");
				dispatch("RoomController?command=channelList&member_id"+member_id, request, response);
			}
			
			
		} else if (command.equals("channelDelete")) {
			
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String msg = "";
			// 유효성 체크
			String adminCheck = chBiz.adminCheck(channel_num);
			System.out.println(adminCheck);
			if (adminCheck.equals(member_id)) {
				int res = chBiz.channelDelete(channel_num);
				 
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
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String channel_access = request.getParameter("channel_access");
			String member_id = request.getParameter("member_id");
			
			String adminCheck = chBiz.adminCheck(channel_num);
			
			if (adminCheck.equals(member_id)) {
				
				ChannelDto dto = new ChannelDto();
				dto.setChannel_seq(channel_num);
				dto.setChannel_name(channel_name);
				dto.setChannel_information(channel_information);
				dto.setChannel_access(channel_access);
				
				int res = chBiz.channelUpdate(dto);
				 
				if (res > 0) {
					System.out.println("채널 정보 수정 성공");
					dispatch("RoomControllerlyj?command=channelList&member_id"+member_id, request, response);
				} else {
					System.out.println("채널 정보 수정 실패");
					dispatch("RoomControllerlyj?command=channelList&member_id"+member_id, request, response);
				}
					
			} else {
				
					System.out.println("채널 정보 수정할 권한이 없습니다.");
			}	
			
		}

	}

	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
