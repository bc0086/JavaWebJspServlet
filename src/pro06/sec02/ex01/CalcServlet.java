package pro06.sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
	
	private static float USD_RATE = 1124.70F;
	private static float JPY_RATE = 10.113F;
	private static float CNY_RATE = 163.30F;
	private static float GBP_RATE = 1444.35F;
	private static float EUR_RATE = 1295.97F;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String command = request.getParameter("command");
			// 수행할 요청을 받아옴
		String won = request.getParameter("won");
			// 변환할 원화를 받아옴
		String operator = request.getParameter("operator");
			// 변화할 외화종류를 받아옴
		
		// 최초 요청시 command가 null이면 계산기 화면을 출력하고,
		// command가 calculate면 계산결과를 출력함
		if(command != null && command.equals("calculate")) {
			String result = calculate(Float.parseFloat(won), operator);
			pw.print("<html><font size=10>변환결과</font><br>");
			pw.print("<html><font size=10>" + result + "</font><br>");
			pw.print("<a href='/JavaWebJspServlet/calc'>환율 계산기 </a>");
			return;
		}
		
		pw.print("<html><title>환율계산기</title>");
		pw.print("<font size=5>환율 계산기</font><br>");
		pw.print("<form  name='frmCalc' method='get'  action='/JavaWebJspServlet/calc'  />  ");
			// 원화 입력 후 다시 서블릿 calc로 요청
		pw.print("원화: <input type='text' name='won' size=10  />  ");
		
		pw.print("<select name='operator' >");
			// 셀렉트 박스에서 선택된 값을 name으로 전송
		pw.print("<option value='dollar'>달러</option>");
		pw.print("<option value='en'>엔화</option>");
		pw.print("<option value='wian'>위안</option>");
		pw.print("<option value='pound'>파운드</option>");
		pw.print("<option value='euro'>유로</option>");
		pw.print("</select>");
		
		pw.print("<input type='hidden' name='command' value='calculate'  />  ");
			// hidden태그를 이용하여 계산기에서 서블릿으로 수행할 요청을 전달
		pw.println("<input type='submit' value='변환'  />");
		pw.println("</form>");
		pw.print("</html>");
		pw.close();
	}
	
	// 원화를 선택한 외화로 환산
	private static String calculate(float won, String operator) {
		String result = null;
		if (operator.equals("dollar")) {
			result = String.format("%.6f", won / USD_RATE);
		} else if (operator.equals("en")) {
			result = String.format("%.6f", won / JPY_RATE);
		} else if (operator.equals("wian")) {
			result = String.format("%.6f", won / CNY_RATE);
		} else if (operator.equals("pound")) {
			result = String.format("%.6f", won / GBP_RATE);
		} else if (operator.equals("euro")) {
			result = String.format("%.6f", won / EUR_RATE);
		}
		return result;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}
	
	@Override
	public void destroy() {
		System.out.println("destory 메서드 호출");
	}
}
