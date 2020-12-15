package pro08.sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex01first")
public class FirstServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		response.sendRedirect("ex01second");
			// sendRedirect()메서드를 이용해 웹브라우저에게 다른 서블릿은 second로 재요청
			// 해당 파일을 실행하면 url이나 내용이 ex01second가 나오게 된다.
	}
}
