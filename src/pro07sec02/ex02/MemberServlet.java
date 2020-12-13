package pro07sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member3")
public class MemberServlet extends HttpServlet {

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		MemberDAO dao = new MemberDAO(); // SQL문으로 조회할 MemberDAO객체를 생성
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command"); // command값을 받아옴
		
		// 회원가입창에서 전송된 command가 addMember이면 전송된 값들을 받아옴
		// 회원가입창에서 전송된 값들을 얻어와 MemberVO객체에 저장한 후 SQL문을 이용해 전달
		if(command != null && command.equals("addMember")) {
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			MemberVO vo = new MemberVO();
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);
			
			dao.addMember(vo);
		} else if(command != null && command.equals("delMember")) {
			String id = request.getParameter("id");
			//dao.delMember(id);
		}
		List<MemberVO> list = dao.listMembers(); // listMembers()메서드로 회원정보를 조회
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor = 'lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td>"
				+ "<td>가입일</td><td>삭제</td></tr>");
		
		// 조회한 회원 정보를 for문과 <tr>태그를 이용해 리스트로 출력
		for(int i =0; i<list.size(); i++) {
			MemberVO memberVO = list.get(i);
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			out.print("<tr>"
				+ "<td>" + id + " </td>"
				+ "<td>" + pwd + "</td>"
				+ "<td>" + name + "</td>"
				+ "<td>" + email + "</td>"
				+ "<td>" + joinDate + "</td>"						
				+ "<td> "
				+ "<a href='/JavaWebJspServlet/member3?command=delMember&id=" + id + "'>삭제</a>"
				+ "</td>"
				+ "</tr>");
		}
		out.print("</table></body></html>");
		out.print("<a href='/JavaWebJspServlet/pro07sec02.ex02.memberForm.html'>새 회원 등록하기 </a>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
}
