package pro06.sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login") // 서블릿의 매핑 이름이 login
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		HttpServletRequest req
			- 웹 브라우저에서 전송한 정보를 톰캣 컨테이너가 HttpServletRequest 객체를 
				생성한 후 doGet()으로 넘겨줌
		*/
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
			// getParameter()를 이용하여 <input>태그의 name 속성 값으로 전송된 value를 받아옴
		
		String user_pw = request.getParameter("user_pw");
		System.out.println("아이디:" + user_id);
		System.out.println("비밀번호:" + user_pw);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}

	@Override
	public void destroy() {
		System.out.println("destory 메서드 호출");
	}
	
}
