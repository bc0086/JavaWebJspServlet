<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*"
	import="pro12.sec02.ex01.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		h1 {
			text-align: center;
		}
	</style>
<meta charset="utf-8">
<title>회원정보 출력창</title>
</head>
<body>
	<h1>회원 정보 출력</h1>
<%
	request.setCharacterEncoding("utf-8");
	String _name = request.getParameter("name"); // 전송된 이름을 가져옴
	
	MemberVO memberVO = new MemberVO();
	memberVO.setName(_name);
	
	MemberDAO dao = new MemberDAO();
	
	List membersList=dao.listMembers(memberVO);
		// memberVO를 listMembers() 메서드로 전달하여 조회 조건에 해당되는 회원정보를 조회
%>
	<table border=1 width=800 align=center>
		<tr align=center bgcolor="#FFFF66">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
<%
		/* MemberDAO에서 조회한 회원 정보를 for 반복문을 이용해 테이블의 행으로 출력 */
		for(int i=0; i<membersList.size(); i++) {
			MemberVO vo = (MemberVO) membersList.get(i);
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String email = vo.getEmail();
			Date joinDate = vo.getJoinDate();
%>
		<tr align=center>
			<td><%= id %></td>
			<td><%= pwd %></td>
			<td><%= name %></td>
			<td><%= email %></td>
			<td><%= joinDate %></td>
		</tr>	
<%
		}
%>
	</table>
</body>
</html>