package channel.controller_lyj;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.lyj_member.MemberBiz;
import channel.lyj_member.MemberBizImpl;
import channel.lyj_room.RoomBiz;
import channel.lyj_room.RoomBizImpl;
import channel.lyj_room.RoomDto;

@WebServlet("/ChatControllerlyj")
public class ChatController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ChatController_lyj() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 채팅 메세지 관련 컨트롤러
		MemberBiz memberBiz = new MemberBizImpl();
		RoomBiz roomBiz = new RoomBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		// 로그인시 어드민으로 로그인 할 경우
		if (command.equals("channelAdminList")) {

			List<RoomDto> list = roomBiz.channelAdminList();

			if (list != null) {
				request.setAttribute("channelAdminlist", list);
				dispatch("./jsp_lyj/admin_lyj.jsp", request, response);
			}
			
		
		// 로그인시 일반 유저로 로그인 할 경우
		} else if (command.equals("channelList")) {
			
			String member_id = request.getParameter("member_id");

			List<RoomDto> list = roomBiz.channelList(member_id);

			if (list != null) {
				request.setAttribute("channellist", list);
				dispatch("./jsp_lyj/main_lyj.jsp", request, response);
			}
			
		}
		
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
