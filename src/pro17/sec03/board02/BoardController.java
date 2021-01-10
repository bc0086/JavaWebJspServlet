package pro17.sec03.board02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\03Workspace/file_repo";
		// 글에 첨부한 이미지 저장 위치를 상수로 선언
	BoardService boardService;
	ArticleVO articleVO;
	
	public void init() throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
			// 서블릿 초기화 시 BoardService객체를 생성
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/pro17_board02/listArticles.jsp";
				
			// action값이 /listArticles.do이면 전체 글을 조회
			} else if (action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
					// 전체글을 조회
				request.setAttribute("articlesList", articlesList);
					// 조회된 글 목록을 articlesList로 바인딩한 후 listArticles.jsp로 포워딩
				nextPage = "/pro17_board02/listArticles.jsp";
				
			// action값이 /articleForm.do이면 글쓰기 창이 나타남	
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/pro17_board02/articleForm.jsp";
			
			// action값이 /addArticle.do이면 새 글 추가작업을 수행함
			} else if (action.equals("/addArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				// articleMap에 저장된 글 정보를 다시 가져옴
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				// 글쓰기 창에서 입력된 정보를 ArticleVO객체에 설정한 후 addArticle()로 전달
				articleVO.setParentNO(0); 
				articleVO.setId("hong"); // 새 글 작성자 ID를 hong으로 설정
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				boardService.addArticle(articleVO);
				nextPage = "/board/listArticles.do";
			
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		Map<String, String> articleMap = new HashMap<String, String>();
		
		// 업로드 파일소스와 거의 유사함
		String encoding="utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO); 
			// 글 이미지 저장 폴더에 대해 파일 객체를 생성
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath); // 파일 경로를 설정
		factory.setSizeThreshold(1024*1024); // 최대 업로드 가능한 파일크기를 설정
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// request객체에서 매개변수를 List로 가져옴
			List items = upload.parseRequest(request); 
			
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
					// 파일 업로드창에서 업로드된 항목들을 하나씩 가져옴
				
				if(fileItem.isFormField()) { // 폼 필드이면 전송된 매개변수 값을 출력
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
						/* 파일 업로드로 같이 전송된 새 글 관련 매개변수를 Map에 
						 * (key, value)로 저장한 후 반환하고, 
						 * 새 글과 관련된 title, content를 Map에 저장함. */
				} else {
					System.out.println("매개변수이름 : " + fileItem.getFieldName());
					System.out.println("파일이름 : " + fileItem.getName());
					System.out.println("파일크기 : " + fileItem.getSize() + "bytes");
					
					if(fileItem.getSize() >0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
							// 업로드 한 파일이름을 가져옴
						articleMap.put(fileItem.getFieldName(), fileName);
							/* 업로드된 파일의 파일이름을 Map의 
							 * ("imageFileName","업로드파일이름")로 저장 */
						File uploadFile = new File(currentDirPath + "\\" + fileName);
							// 업로드 한 파일이름으로 저장소에 파일을 업로드함.
						fileItem.write(uploadFile);
					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	

}
