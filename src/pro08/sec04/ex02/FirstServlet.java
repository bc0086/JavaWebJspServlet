package pro08.sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sec04ex02first")
public class FirstServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		request.setAttribute("address", "서울시 성북구");
			// 웹 브라우저에서 요청한 request객체에 address값으로 "서울시 성북구"를 바인딩
		RequestDispatcher dispatch = request.getRequestDispatcher("sec04ex02second");
		dispatch.forward(request, response);
	}
}
