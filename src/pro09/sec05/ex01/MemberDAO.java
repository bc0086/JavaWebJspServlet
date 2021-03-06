package pro09.sec05.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			// JNDI에 접근하기 위해 기본 경로("java:/comp/env") 지정, 거의 고정됨
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
				// 톰캣의 context.xml에 설정한 name값이 jdbc/oracle을 이용해
				// 톰캣이 미리 연결한 DataSource를 받아온다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원정보 조회
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			// DataSource를 이용해 DB에 연결
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			System.out.println("preparedStatement: " + query);
			
			pstmt = con.prepareStatement(query);
				// prepareStatement()메서드에 SQL문을 전달하여 PreparedStatement객체 생성
			
			ResultSet rs = pstmt.executeQuery(query);
				// executeQuery()메서드를 호출하여 미리 설정한 SQL문을 실행
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				list.add(vo); 
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}	
	
	// 회원등록
	public void addMember(MemberVO memberVO) {
		try {
			con = dataFactory.getConnection();
			
			// 테이블에 저장할 회원 정보를 받아옴
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			// insert문을 문자열로 만든다.
			String query = "insert into t_member";
			query += " (id, pwd, name, email)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatement: " + query);
			
			// insert문에 각 ?에 순서대로 회원정보를 셋팅
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate(); // 회원정보를 테이블에 추가
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원 삭제
	public void delMember(String id) {
		try {
			con = dataFactory.getConnection();
			
			// delete문을 문자열로 만든다.
			String query = " delete from t_member" + " where id = ?";
			System.out.println("preapredStatement:" + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id); // 첫 번째 ?에 전달된 ID를 인자로 넣는다.
			pstmt.executeUpdate(); // delete문으로 테이블에서 해당 ID의 회원정보를 삭제
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 세션을 이용한 로그인
	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		try {
			con = dataFactory.getConnection();
			
			// 오라클의 decode() 함수를 이용해 조회하여 ID와 비밀번호가 테이블에 
			// 존재하면 true 아니면 false를 조회
			String query = "select decode(count(*),1,'true','false') as result from t_member";
			query += " where id=? and pwd=?";
			System.out.println(query);
			
			// 메서드로 전달된 ID와 비밀번호를 이용해 SQL문을 작성한 후 데이터베이스와 조회
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
