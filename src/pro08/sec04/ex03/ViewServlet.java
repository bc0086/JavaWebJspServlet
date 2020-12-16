package pro08.sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List membersList = (List) request.getAttribute("membersList");
			// 바인딩해서 넘어온 request에서 회원 정보를 가져옴
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor = 'lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td>"
				+ "<td>가입일</td><td>삭제</td></tr>");
		
		// 조회한 회원 정보를 for문과 <tr>태그를 이용해 리스트로 출력
		for(int i =0; i<membersList.size(); i++) {
			MemberVO memberVO = (MemberVO) membersList.get(i);
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
					// 삭제를 클릭하면 command값과 회원 ID를 서블릿으로 전송 
				+ "</td>"
				+ "</tr>");
		}
		out.print("</table></body></html>");
		out.print("<a href='/JavaWebJspServlet/pro07.sec02.ex02.memberForm.html'>새 회원 등록하기 </a>");
	}

}
