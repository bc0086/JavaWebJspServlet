package pro17.sec03.board02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		boardDAO = new BoardDAO(); // 생성자 호출 시 BoardDAO객체를 생성
	}
	
	// 게시판 글 전체목록 보기
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	// 게시판 글 작성하기
	public void addArticle(ArticleVO article) {
		boardDAO.insertNewArticle(article);
	}

}
