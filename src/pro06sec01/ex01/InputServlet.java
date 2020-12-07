package pro06sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
			// 한 개씩 전송된 값은 getParameter()를 이용함
		System.out.println("아이디:" + user_id);
		System.out.println("비밀번호:" + user_pw);
		
		// 하나의 name으로 여러 값을 전송하는 경우 getParameterValues()를 이용해 배열형태로 받음
		String[] subject = request.getParameterValues("subject");
		for(String str : subject) {
			System.out.println("선택한 과목:" + str);
		}
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
