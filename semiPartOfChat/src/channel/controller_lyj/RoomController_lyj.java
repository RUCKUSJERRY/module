package channel.controller_lyj;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.lyj_room.RoomBiz;
import channel.lyj_room.RoomDto;

@WebServlet("/RoomController")
public class RoomController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RoomController_lyj() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//채널 룸 출력,생성,수정,삭제 관련 서블릿
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		RoomBiz biz = new RoomBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		if (command.equals("channelAdminList")) {
			List<RoomDto> list = biz.channelAdminList();

			if (list != null) {
				request.setAttribute("channelAdminlist", list);
				dispatch("admin.jsp", request, response);
			}
		} else if (command.equals("channelList")) {
			
		}
		
		
		
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
