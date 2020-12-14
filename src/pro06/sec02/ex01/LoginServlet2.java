package pro06.sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpServletResponse response : 응답을 할 때 사용되는 객체
		
		request.setCharacterEncoding("utf-8");
			// 웹 브라우저에서 전송되는 데이터의 인코딩을 설정
		
		response.setContentType("text/html; charset=utf-8");
			// setContentType()을 이용해 응답할 데이터 종류가 HTML임을 설정
		
		PrintWriter out = response.getWriter();
			// HttpServiceResponse 객체의 getWriter()를 이용해 출력 스트림 PrintWriter 객체를 받아옴
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		// 브라우저로 출력할 데이터를 문자열로 연결해서 HTML 코드로 만듬
		String data = "<html>";
			data += "<body>";
			data += "아이디 : " + id;
			data += "<br>";
			data += "패스워드 : " + pw;
			data += "</body>";
			data += "</html>";
			
		// PrintWriter의 print()를 이용해 HTML 태그 문자열을 웹 브라우저로 출력
		out.print(data);
	}
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	public void destroy() {
		System.out.println("destory 메서드 호출");
	}
}
