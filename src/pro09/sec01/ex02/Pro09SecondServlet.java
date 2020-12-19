package pro09.sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pro09second")
public class Pro09SecondServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 첫번 째 서블릿에서 전송한 로그인 정보를 가져옴
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		
		out.println("<html><body>");
		if(user_id != null && user_id.length() != 0) {
			out.println("이미 로그인 상태입니다. <br>");
			out.println("첫 번째 서블릿에서 넘겨준 아이디 : " + user_id + "<br>");
			out.println("첫 번째 서블릿에서 넘겨준 비밀번호 : " + user_pw + "<br>");
			out.println("첫 번째 서블릿에서 넘겨준 주소 : " + user_address + "<br>");
			out.println("</body></html>");
		} else {
			out.println("로그인 하지 않았습니다.<br><br>");
			out.println("다시 로그인 하세요.<br><br>");
			out.println("<a href = '/JavaWebJspServlet/pro09.sec01.ex02.login.html'>로그인창으로 이동하기</>");
				// a태그 안에서 띄어쓰기 금물
		}
	}
	
	public void init() {
		System.out.println("init 메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}
}
