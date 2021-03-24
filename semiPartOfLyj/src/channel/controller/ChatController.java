package channel.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.room.biz.RoomBiz;
import channel.room.dao.RoomDao;
import channel.room.dto.RoomDto;
import channel.room.dto.RoomMemberDto;

@WebServlet("/ChatController")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		RoomBiz biz = new RoomBiz();
		if (command.equals("channeladd")) {
			
			String channelname = request.getParameter("channelname");
			String channelinfo = request.getParameter("channelinfo");
			String access = request.getParameter("access");
			String channelmaster = request.getParameter("channelmaster");
			
			RoomDto dto = new RoomDto();
			dto.setChannel_name(channelname);
			dto.setChannel_information(channelinfo);
			dto.setChannel_access(access);
			dto.setChannel_master(channelmaster);
			
			RoomMemberDto roomDto = new RoomMemberDto();
			roomDto.setChannel_name(channelname);
			roomDto.setMember_id(channelmaster);
			
			int res = biz.createRoom(dto);
			int resAdd = biz.roomMemberAdd(roomDto);
			
			if (res > 0 && resAdd > 0) {	
				System.out.println("채널 생성 완료");
				dispatch("main.jsp", request, response);
			} else {
				System.out.println("채널 생성 실패");
				dispatch("main.jsp", request, response);
			}
			
		} else if (command.equals("channelList")) {
			
			String id = request.getParameter("id");
			
			
		}
		
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
