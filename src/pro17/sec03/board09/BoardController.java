package pro17.sec03.board09;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

//@WebServlet("/board/*")
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
		HttpSession session; // 답글에 대한 부모 글 번호를 저장하기 위해 세션 사용
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		try { 
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				// 페이징 처리
				// 최초 요청 시 또는 /listArticle.do로 요청 시 section값과 pageNum값을 구함
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				
				// 최초 요청 시 section값과 pageNum값이 없으면 각각 1로 초기화
				int section = Integer.parseInt(_section==null ? "1" : _section);
				int pageNum = Integer.parseInt(_pageNum==null ? "1" : _pageNum);
				
				// section값과 pageNum값을 HashMap에 저장한 후 메서드로 넘김
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				// section값과 pagingNum값으로 해당 섹션과 페이지에 해당되는 글 목록을 조회
				Map articlesMap = boardService.listArticles(pagingMap);
				
				// 브라우저에서 전송한 section과 pagingNum값을 articlesMap에 저장한 후 
				// listArticles.jsp로 넘김
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				
				// 조회된 글 목록을 articlesMap으로 바인딩하여 listArticles.jsp로 넘김
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/pro17_board09/listArticles.jsp";
				
			// action값이 /listArticles.do이면 전체 글을 조회
			} else if (action.equals("/listArticles.do")) {
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				int section = Integer.parseInt(_section==null ? "1" : _section);
				int pageNum = Integer.parseInt(_pageNum==null ? "1" : _pageNum);
				
				Map pagingMap = new HashMap();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);

				Map articlesMap = boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/pro17_board09/listArticles.jsp";
				
			// action값이 /articleForm.do이면 글쓰기 창이 나타남	
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/pro17_board09/articleForm.jsp";
			
			// action값이 /addArticle.do이면 새 글 추가작업을 수행함
			} else if (action.equals("/addArticle.do")) {
				int articleNO = 0;
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
				
				articleNO = boardService.addArticle(articleVO);
					// 테이블에 새 글을 추가한 후 새글에 대한 글 번호를 가져옴
				
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\"+"temp"+"\\"+imageFileName);
						// temp 폴더에 임시로 업로드 된 파일 객체를 생성함.
					File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO );
					destDir.mkdirs();
						// CURR_IMAGE_REPO_PATH의 경로 하위에 글 번호로 폴더를 생성
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
						// temp 폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동시킴
				}
				
				/* 새 글 등록 메세지를 나타낸 후 자바스크립트 location 객체의 href 속성을
				 * 이용해 글 목록을 요청함 */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
						+ " alert('새 글을 추가했습니다.');"
						+ " location.href='"+ request.getContextPath()+ "/board/listArticles.do';" 
						+ "</script>");
				return;
				
			// action값이 /viewArticle.do이면 글 상세보기 작업을 수행함
			} else if(action.equals("/viewArticle.do")) {
				String articleNO = request.getParameter("articleNO");
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO)); 
				request.setAttribute("article", articleVO);
					// articleVO에 대한 글 정보를 조회하고 acticle 속성으로 바인딩
				nextPage = "/pro17_board09/viewArticle.jsp";
			
			// action값이 /modArticle.do이면 글 수정하기 작업을 수행함
			} else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNO);
				
				// articleMap에 저장된 글 정보를 다시 가져옴
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				// 글쓰기 창에서 입력된 정보를 ArticleVO객체에 설정
				articleVO.setParentNO(0); 
				articleVO.setId("hong"); // 새 글 작성자 ID를 hong으로 설정
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				// 전송된 글 정보를 이용해 글을 수정함.
				boardService.modArticle(articleVO);
				
				if(imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					
					// 수정된 이미지 파일을 폴더로 이동시키는 작업
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\"+"temp"+"\\"+imageFileName);
						// temp 폴더에 임시로 업로드 된 파일 객체를 생성함.
					File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO );
					destDir.mkdirs();
						// CURR_IMAGE_REPO_PATH의 경로 하위에 글 번호로 폴더를 생성
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
						// temp 폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동시킴
					
					// 전송된 origanalImageFileName을 이용해 기존의 파일을 삭제
					File oldFile = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO+"\\"+originalFileName);
					oldFile.delete();
				}
				/* 새 글 등록 메세지를 나타낸 후 자바스크립트 location 객체의 href 속성을
				 * 이용해 글 상세화면을 나타냄 */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
						+ " alert('수정되었습니다.');"
						+ " location.href='"+ request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" 
						+ "</script>");
				return;
			
			// action값이 /removeArticle.do이면 글 삭제 작업을 수행
			} else if(action.equals("/removeArticle.do")) {
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				
				// articleNO값에 대한 글을 삭제한 후 삭제된 부모 글과 자식글의 articleNO목록을 가져옴
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				
				// 삭제된 글들의 이미지 저장 폴더들을 삭제
				for(int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
						+ " alert('글이 삭제되었습니다.');"
						+ " location.href='"+ request.getContextPath()
						+ "/board/listArticles.do';" 
						+ "</script>");
				return;
				
			// action값이 /replyForm.do이면 답글창 요청기능 수행
			} else if(action.equals("/replyForm.do")) {
				// 답글 창 요청 시 미리 부모 글 번호를 parentVO속성으로 세션에 저장
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				nextPage = "/pro17_board09/replyForm.jsp";
				
			// action값이 /addReply.do이면 답글창 요청기능 수행
			} else if(action.equals("/addReply.do")) {
				// 답글 전송 시 세션에 저장된 parentNO를 가져옴
				session = request.getSession();
				int parentNO = (Integer) session.getAttribute("parentNO");
				session.removeAttribute("parentNO");
				
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				// 글쓰기 창에서 입력된 정보를 ArticleVO객체에 설정
				articleVO.setParentNO(parentNO); // 답글의 부모 글 번호를 설정
				articleVO.setId("lee");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				// 답글에 테이블을 추가
				int articleNO = boardService.addReply(articleVO);
				
				if(imageFileName != null && imageFileName.length() !=0 ) {
					File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
						// temo폴더에 임시로 업로드 된 파일 객체를 생성함.
					
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					destDir.mkdirs();
						// CURR_IMAGE_REPO_PATH의 경로 하위에 글 번호로 폴더를 생성
							
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
						// temp 폴더의 파일을 글 번호를 이름으로 하는 폴더로 이동시킴
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
						+ " alert('답글이 추가되었습니다.');"
						+ " location.href='"+ request.getContextPath()
						+ "/board/viewArticle.do?articleNO="+ articleNO + "';" 
						+ "</script>");
				return;
			} else {
				nextPage = "/pro17_board09/listArticles.jsp";
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
							//익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
							// 첨부한 파일을 먼저 temp폴더에 업로드함
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
