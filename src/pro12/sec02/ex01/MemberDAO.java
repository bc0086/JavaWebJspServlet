package pro12.sec02.ex01;

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
	public List listMembers(MemberVO memberVO){
		List membersList = new ArrayList();
		String _name = memberVO.getName();
		try {
			// DataSource를 이용해 DB에 연결
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			
			if(_name != null && _name.length() != 0) {
				query += " where name=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, _name); // 1번째 ?에 전달된 이름을 지정
			} else { // name값이 없으면 모든 회원정보를 조회
				pstmt = con.prepareStatement(query);
					// prepareStatement()메서드에 SQL문을 전달하여 PreparedStatement객체 생성
			}
			
			System.out.println("preparedStatement: " + query);
			ResultSet rs = pstmt.executeQuery();
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
				
				membersList.add(vo); 
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membersList;
	}	
}
