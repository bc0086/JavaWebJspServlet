package pro17.sec03.board03;

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
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
			// 새 글 번호를 컨트롤러로 반환
	}

}
