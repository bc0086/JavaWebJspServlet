<%@page import="org.apache.catalina.tribes.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*, pro13.sec01.ex01.*"
    pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	/* 전송된 회원정보를 getParameter를 통해 가져옴 */
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	/* MemberBean 객체 생성하여 각 회원정보를 속성에 설정 */
	MemberBean m = new MemberBean(id, pwd, name, email);
	
	MemberDAO memberDAO = new MemberDAO();
	memberDAO.addMember(m); // 회원 정보를 테이블에 추가
	
	List membersList = memberDAO.listMembers(); // 전체 회원 정보를 조회
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원 목록창</title>
</head>
<body>
	<table align="center"  width="100%">
		<tr align="center" bgcolor="#99ccff">
	    	<td width="7%" >아이디</td>
	      	<td width="7%">비밀번호</td>
	      	<td width="5%" >이름</td>
	      	<td width="11%" >이메일</td>
	      	<td width="5%" >가입일</td>
	   	</tr>
<% 	   	
	   	if(membersList.size() == 0) {
%>
			<tr>
				<td colspan="5">
					<p align="center">
						<b><span style="font-size:9pt;">등록된 회원이  없습니다.</span></b>
					</p>
				</td>
			</tr>
<%	   	
	   	} else {
	   		/* for반복문을 통해 membersList의 저장된 MemberBean객체를 한 개씩 가져온 후 
	   			각 속성의 회원 정보를 다시 getter를 이용해 출력한다 */
	   		for(int i = 0; i < membersList.size(); i++) {
	   			MemberBean bean = (MemberBean) membersList.get(i);
%>
			<tr align="center">
	       		<td><%=bean.getId() %></td>
	       		<td><%=bean.getPwd() %></td>
	       		<td><%=bean.getName() %></td>
	       		<td><%=bean.getEmail() %></td>
	       		<td><%=bean.getJoinDate() %></td>
	   		</tr>
<% 
	   		}
	   	}
%>
		<tr height="1" bgcolor="#99ccff">
	    	<td colspan="5"></td>
		</tr>
	</table>
</body>
</html>