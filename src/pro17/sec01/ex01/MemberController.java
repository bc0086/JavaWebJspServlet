package pro17.sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem.do")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		List<MemberVO> membersList = memberDAO.listMembers(); 
			// 요청에 대한 회원정보 조회 
		request.setAttribute("membersList", membersList); 
			// 조회한 회원정보를 request에 바인딩
		RequestDispatcher dispatch = request.getRequestDispatcher("/pro17/listMembers.jsp");
		dispatch.forward(request, response);
			// 컨트롤러에서 표시하고자 하는 JSP로 포워딩
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


}