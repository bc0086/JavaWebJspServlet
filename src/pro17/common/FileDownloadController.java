package pro17.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	
	private static String ARTICLE_IMAGE_REPO = "C:\\03Workspace/file_repo";
		// 글에 첨부한 이미지 저장 위치를 상수로 선언
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 이미지 파일 이름과 글 번호를 가져옴
		String imageFileName = (String)request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");
		System.out.println("imageFileName : " + imageFileName);
		
		// response에서 OutputStream객체를 가져옴
		OutputStream out = response.getOutputStream();
		String path = ARTICLE_IMAGE_REPO + "\\"+ articleNO + "\\" + imageFileName;
			// 글 번호에 대한 파일 경로를 설정함
		File imageFile = new File(path);
		
		// 파일을 다운로드 할 수 있게하는 설정.
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+imageFileName);
		
		FileInputStream in = new FileInputStream(imageFile);
		
		// 버퍼기능을 이용해 파일에서 버퍼로 데이터를 읽어와 한꺼번에 출력함
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if(count==-1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
