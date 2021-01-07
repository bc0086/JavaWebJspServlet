package pro17.sec01.ex01;

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
	
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
				// prepareStatement 객체를 생성하면서 SQL문을 인자로 전달
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
					// 조회한 회원정보를 레코드별로 MemberVO객체의 속성에 저장
				membersList.add(memberVO);
					// membersList에 MemberVO객체들을 차례대로 저장
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
