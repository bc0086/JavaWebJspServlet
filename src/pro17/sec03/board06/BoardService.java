package pro17.sec03.board06;

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

}
