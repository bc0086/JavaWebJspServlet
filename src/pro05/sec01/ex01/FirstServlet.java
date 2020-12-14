package pro05.sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	// 사용자 정의 서블릿은 반드시 HttpServlet을 상속받아야 한다.

	@Override
	public void init() throws ServletException {
		System.out.println("init메서드를 호출해봅니다.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet메서드를 호출해봅니다.");
	}

	@Override
	public void destroy() {
		System.out.println("destory메서드를 호출해봅니다.");
	}

	
}
