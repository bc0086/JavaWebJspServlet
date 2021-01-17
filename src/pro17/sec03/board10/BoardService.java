package pro17.sec03.board10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		boardDAO = new BoardDAO(); // 생성자 호출 시 BoardDAO객체를 생성
	}
	
	// 페이징 처리
	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articlesMap = new HashMap();
		
		// 공지글 여부에 따라 변형
		List<ArticleVO> noticeArticleList = boardDAO.selectAllArticles(pagingMap,"y");
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap,"n");
		
		// 테이블에 존재하는 전체 글 수를 조회
		int totArticles = boardDAO.selectTotArticles();
		
		// 조회된 글 목록을 ArrayList에 저장한 후 다시 articlesMap에 저장
		articlesMap.put("noticeArticlesList", noticeArticleList);
		
		// 조회된 글 목록을 ArrayList에 저장한 후 다시 articlesMap에 저장
		articlesMap.put("articlesList", articlesList);

		// 전체 글 수를 articlesMap에 저장
		articlesMap.put("totArticles", totArticles);
		//articlesMap.put("totArticles", 170);
		return articlesMap;
	}
	
	// 게시판 글 전체목록 보기
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	// 게시판 글 작성하기
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
			// 새 글 번호를 컨트롤러로 반환
	}

	// 게시판 글 번호와 글 정보를 받아오기
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
	}

	// 게시판 글 수정하기
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}

	// 게시판 글 삭제하기
	public List<Integer> removeArticle(int articleNO) {
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
			// 글을 삭제하기 전 글 번호들을 ArrayList로 객체에 저장함.
		boardDAO.deleteArticle(articleNO);
		return articleNOList; // 삭제한 글 번호 목록을 컨트롤러로 반환
	}

	// 게시판 답글 쓰기
	public int addReply(ArticleVO article) {
		// 새 글 추가시 사용한 insertNewArticle() 메서드를 이용해 답글을 추가함
		return boardDAO.insertNewArticle(article);
	}

}
