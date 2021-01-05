package pro16.sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import pro12.sec02.ex01.MemberVO;

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

	public boolean overlappedID(String id) {
		boolean result = false;
		try {
			con = dataFactory.getConnection();
			
			String query = "select decode(count(*),1,'true','false') as result from t_member";
			query += " where id=?";
			System.out.println("prepareStatement: " + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
