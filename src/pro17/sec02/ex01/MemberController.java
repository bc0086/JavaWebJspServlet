package pro17.sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*") // 브라우저에서 요청 시 두단계로 요청이 이루어짐
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String nextPage = null;
		String action = request.getPathInfo(); // URL에서 요청명을 가져옴
		System.out.println("action : " + action);
		
		if(action == null || action.equals("/listMembers.do")) {
			List<MemberVO> membersList = memberDAO.listMembers(); 
				// 요청에 대한 회원정보 조회 
			request.setAttribute("membersList", membersList); 
				// 조회한 회원정보를 request에 바인딩
			nextPage = "/pro17/listMembers.jsp"; 
				// pro17폴더의 listMember.jsp로 포워딩
		
		// action값이 addMember.do이면 전송된 회원정보를 가져와 테이블에 추가
		} else if(action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/pro17/listMembers.do";
				// 회원등록 후 다시 회원목록을 출력
			
		// action값이 memberForm.do이면 전송된 회원정보를 가져와 테이블에 추가
		} else if(action.equals("/memberForm.do")) {
			nextPage = "pro17.memberForm.jsp"; // 해당 jsp로 포워딩
			
		// 그 외 다른 action값은 회원목록을 출력
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "pro17/listMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			// nextPage에 지정한 요청명으로 다시 서블릿에 요청
		dispatch.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


}
