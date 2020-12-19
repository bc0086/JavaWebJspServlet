package pro09.sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pro09set")
public class SetCookieValue extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Date d = new Date();
		
		// Cookie 객체를 생성한 후 cookieTest 이름으로 한글정보를 인코딩해서 쿠키에 저장
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", "utf-8"));
		// c.setMaxAge(24*60*60); // 유효 기간을 설정
		c.setMaxAge(-1); // 유효 기간이 음수면 세션쿠키가 생성됨
		
		response.addCookie(c); // 생성된 쿠키를 브라우저로 전송
		
		out.println("현재시간 : " + d);
		out.println("문자열을 Cookie에 저장");
	}

}
