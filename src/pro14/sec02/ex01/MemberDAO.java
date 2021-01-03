package pro14.sec02.ex01;

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

	// 자바빈을 이용한 회원정보 조회
	public List listMembers(){
		List list = new ArrayList();
		try {
			// DataSource를 이용해 DB에 연결
			con = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc ";
			System.out.println("preparedStatement: " + query);
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
				// executeQuery()메서드를 호출하여 미리 설정한 SQL문을 실행

			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				// 조회한 회원정보를 MemberBean객체의 속성에 저장한 후 다시 ArrayList에 저장
				MemberBean vo = new MemberBean();
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
	
	// 자바빈을 이용한 회원 등록
	// MemberBean 객체에 저장된 회원 정보를 전달
	public void addMember(MemberBean memberBean) {
		try {
			Connection con = dataFactory.getConnection();
			
			// getter를 이용해 회원 정보를 가져옴
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			
			// 회원정보를 추가
			String query = "insert into t_member";
			query += " (id, pwd, name, email)";
			query += " values(?, ?, ?, ?)";
			System.out.println("prepareStatement: " + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
