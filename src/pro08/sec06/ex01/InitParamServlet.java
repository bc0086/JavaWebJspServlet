package pro08.sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { 
				"/sInit", 
				"/sInit2"
		}, 
		initParams = { 
				@WebInitParam(name = "email", value = "admin@jweb.com"), 
					// urlPatterns를 이용해 매칭 이름을 여러개 설정 할 수 있다.
				@WebInitParam(name = "tel", value = "010-1111-2222")
					// @WebinitParam을 이용해 여러 개의 매개변수를 설정할 수 있다.
		})
public class InitParamServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 설정한 매개변수와 name으로 값을 가져온다.
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td>email : </td><td>" + email + "</td></tr>");
		out.print("<tr><td> 휴대전화 : </td></td>" + tel + "</td>");
		out.print("</tr></table></body></html>");
		out.close();
	}
}
