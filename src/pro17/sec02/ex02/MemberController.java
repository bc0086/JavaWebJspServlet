package pro17.sec02.ex02;

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
			nextPage = "/member/listMembers.do";
				// 회원등록 후 다시 회원목록을 출력
			
		// action값이 memberForm.do이면 전송된 회원정보를 가져와 테이블에 추가
		} else if(action.equals("/memberForm.do")) {
			nextPage = "/pro17/memberForm.jsp"; // 해당 jsp로 포워딩

		// 회원 수정창 요청 시 ID로 회원정보를 조회한 후 수정창으로 포워딩
		} else if(action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);
				// 회원정보 수정창을 요청하면서 전송된 ID를 이용해 수정 전 회원정보 조회
			request.setAttribute("memInfo", memInfo);
				// request에 바인딩하여 회원정보 수정창에 수정하기 전 회원정보를 전달
			nextPage = "/pro17/modMemberForm.jsp";
		
		// 테이블의 회원정보를 수정
		} else if(action.equals("/modMember.do")) {
			// 회원정보 수정창에서 전송된 수정회원 정보를 가져온 후 MemberVO()객체 속성에 저장
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			
			// 회원 목록창으로 수정 작업 완료 메세지를 전달
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";
		
		// 회원ID를 SQL문으로 전달해 테이블의 회원정보를 삭제
		} else if(action.equals("/delMember.do")) {
			// 삭제할 회원ID를 받아옴
			String id = request.getParameter("id");
			// 회원 목록창으로 삭제 작업 완료 메세지를 전달함
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
			
		// 그 외 다른 action값은 회원목록을 출력
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/pro17/listMembers.jsp";
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
