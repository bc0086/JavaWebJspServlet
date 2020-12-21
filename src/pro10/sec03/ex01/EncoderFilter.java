package pro10.sec03.ex01;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*") 
	// WebFilter 애너테이션을 이용해 모든 요청이 필터를 거치게 함

public class EncoderFilter implements Filter { 
	// 사용자 정의 필터는 반드시 Filter 인터페이스를 구현해야 함
	
	ServletContext context;
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8인코딩........");
		context = fConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter호출");
		request.setCharacterEncoding("utf-8"); // 한글 인코딩 설정 작업
		
		String context = ((HttpServletRequest)request).getContextPath();
			// 웹 애플리케이션의 컨텍스트 이름을 가져옴
		String pathinfo = ((HttpServletRequest)request).getRequestURI();
			// 웹 브라우저에서 요청한 요청 URI를 가져옴
		String realPath = request.getRealPath(pathinfo);
			// 요청 URI의 실제 경로를 가져옴
		
		String mesg = " Context 정보 : " + context
				+ "\n URI 정보 : " + pathinfo
				+ "\n 물리적 정보 : " + realPath;
		System.out.println(mesg);
		
		long begin = System.currentTimeMillis(); // 요청 필터에서 요청처리 전의 시각을 구함
		
		// 다음 필터로 넘기는 작업을 수행 
		// chain.doFilter(request, response) 이전은 요청필터 기능, 이후는 응답 필터 기능
		chain.doFilter(request, response); 
		
		long end = System.currentTimeMillis(); // 응답 필터에서 요청처리 후의 시각을 구함
		System.out.println("작업시간 : " + (end - begin) + "ms");
			// 작업 요청 전과 후의 시각 차를 구해 작업 수행 시간을 구함
	}
	
	public void destroy() {
		System.out.println("destory 호출");
	}

}
