package pro17.sec03.board10;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.ws.RespectBinding;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 페이징 처리1. 전체글 조회
	public List selectAllArticles(Map pagingMap) {
		List articlesList = new ArrayList();
		int section = (Integer) pagingMap.get("section");
		int pageNum = (Integer) pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM ("
						    	+ " select ROWNUM as recNum," 
						        	+ " LVL,"
						            + " articleNO,"
						            + " parentNO,"
						            + " title,"
						            + " id,"
						            + " writeDate,"
						            + " newArticle"
						        + " from ("
						            + " select LEVEL as LVL,"						             
						            	+ " articleNO,"        
						            	+ " parentNO,"
						            	+ " title,"
						            	+ " id,"
						            	+ " writeDate,"
						            	+ " decode(round(sysdate - writedate),0,'true','false') newArticle"
						            + " FROM t_board"
						            + " START WITH parentNO=0"
						            + " CONNECT BY PRIOR articleNO=parentNO"
						            + " ORDER SIBLINGS BY articleNO DESC)"
						    + " )"
						    + " WHERE recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10"; 
			System.out.println(query);
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("lvl");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
			    String id = rs.getString("id");
			    Date writeDate= rs.getDate("writeDate");
			    boolean newArticle = Boolean.parseBoolean(rs.getString("newArticle"));
			    
			    // 글 정보를 ArticleVO 객체의 속성에 설정
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setId(id);
				article.setWriteDate(writeDate);
				article.setNewArticle(newArticle);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	public List selectAllArticles(Map pagingMap, String notice_yn) {
		List articlesList = new ArrayList();
		int section = (Integer) pagingMap.get("section");
		int pageNum = (Integer) pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM ("
						    	+ " select ROWNUM as recNum," 
						        	+ " LVL,"
						            + " articleNO,"
						            + " parentNO,"
						            + " title,"
						            + " id,"
						            + " writeDate,"
						            + " newArticle"
						        + " from ("
						            + " select LEVEL as LVL,"						             
						            	+ " articleNO,"        
						            	+ " parentNO,"
						            	+ " title,"
						            	+ " id,"
						            	+ " writeDate,"
						            	+ " decode(round(sysdate - writedate),0,'true','false') newArticle"
						            + " FROM t_board"
						            + " WHERE notice_yn=?"
						            + " START WITH parentNO=0"
						            + " CONNECT BY PRIOR articleNO=parentNO"
						            + " ORDER SIBLINGS BY articleNO DESC)"
						    + " )"
						    + " WHERE recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10"; 
			System.out.println(query);
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, notice_yn);
			pstmt.setInt(2, section);
			pstmt.setInt(3, pageNum);
			pstmt.setInt(4, section);
			pstmt.setInt(5, pageNum);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("lvl");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
			    String id = rs.getString("id");
			    Date writeDate= rs.getDate("writeDate");
			    boolean newArticle = Boolean.parseBoolean(rs.getString("newArticle"));
			    
			    // 글 정보를 ArticleVO 객체의 속성에 설정
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setId(id);
				article.setWriteDate(writeDate);
				article.setNewArticle(newArticle);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	// 페이징처리 2. 전체 글의 갯수 조회
	public int selectTotArticles() {
		try {
			conn = dataFactory.getConnection();
			String query = "select count(articleNO) from t_board";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 게시판 글 목록 보기
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			// 오라클의 계층형 SQL문을 실행
			String query = 
					"SELECT LEVEL, articleNO, parentNO, title, content, writeDate, id"
					+ " FROM t_board"
					+ " START WITH parentNO=0" 
					+ " CONNECT BY PRIOR articleNO = parentNO" 
					+ " ORDER SIBLINGS BY articleNO DESC ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level"); // 각 글의 깊이(계층)을 level속성에 저장
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				// 글 정보를 ArticleVO 객체의 속성에 설정
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}

	// 새 글 작성전 새 글에 대한 글 번호 가져오기
	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT max(articleNO) from t_board";
				// 기본 글 번호 중 가장 큰 번호를 조회
			System.out.println("query");
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// 가장 큰 번호에 1을 더한 번호를 반환
				return (rs.getInt(1) + 1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 게시판 새 글 작성하기
	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
			// 새 글을 추가하기 전에 새 글에 대한 글 번호를 가져옴
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String notice_yn = article.getNotice_yn();
			String query = "INSERT INTO t_board (articleNO, parentNO, title, "
					+ " content, imageFileName, id, notice_yn)"
					+ " VALUES(?,?,?,?,?,?,?)";
			System.out.println("query");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.setString(7, notice_yn);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNO;
			// SQL문으로 새 글을 추가하고 새 글 번호를 반환함
	}

	// 게시판 글 번호와 글 정보를 받아오기
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			// 전달받은 글 번호를 이용해 글 정보를 조회
			String query = "select articleNO, parentNO, title, content, imageFileName, "
						+ " id, writeDate"
						+ " from t_board"
						+ " where articleNO=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int _articleNO = rs.getInt("articleNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = rs.getString("imageFileName");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			
			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	// 게시판 글 수정하기
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_board set title=?,content=?";
			
			// 수정된 이미지 파일이 있을 떄만 imageFileName을 SQL문에 추가함
			if (imageFileName != null && imageFileName.length() != 0) {
				query += ",imageFileName=?";
			} 
			query += " where articleNO=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			
			// 이미지 파일을 수정하는 경우와 그렇지 않은 경우를 구분해서 설정
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 삭제할 글에 대한 글 번호를 가져옴
	public List<Integer> selectRemovedArticles(int articleNO) {
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT articleNO FROM t_board";
			query += " START WITH articleNO = ?";
			query += " CONNECT BY PRIOR articleNO = parentNO";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleNO = rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}
	
	// 전달된 articleNO에 대한 글을 삭제
	public void deleteArticle(int articleNO) {
		try {
			conn = dataFactory.getConnection();
			
			// 오라클의 계층형 SQL문을 이용해 삭제 글과 관련된 자식 글까지 모두 삭제
			String query = " DELETE FROM t_board";
			query += " WHERE articleNO in (";
			query += " SELECT articleNO FROM t_board";
			query += " START WITH articleNO = ?";
			query += " CONNECT BY PRIOR articleNO = parentNO )";
			System.out.println("query");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
