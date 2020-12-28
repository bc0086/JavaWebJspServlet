<%@page import="org.apache.catalina.tribes.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*, pro13.sec01.ex01.*"
    pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!-- 2. useBean액션 태그로 id가 m인 MemberBean객체를 생성함 -->
<jsp:useBean id="m" class="pro13.sec01.ex01.MemberBean" scope="page" />

<!-- 3. 회원가입창에서 전송된 동일한 이름의 매개변수에 해당되는 유즈빈 속성에 전송된 값을 설정 -->
<!-- 4. 회원가입창에서 전달된 매개변수 이름과 속성이름이 같으면 같은 이름으로 값을 설정 -->
<!-- 5. 회원가입창에서 전달된 매개변수 이름이 일치하는 useBean속성에 자동으로 값을 설정 -->
<!-- 6. 전송된 매개변수 이름과 빈 속성을 비교한 후 동일한 빈에 값을 자동으로 설정 -->
<jsp:setProperty name="m" property="*" />
<%
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
	   	
		<!-- 7. <jsp:getProperty>태그를 이용해 useBean 속성 값에 접근함 -->	   	
		<tr align="center">
			<td> <jsp:getProperty name="m"  property="id"  /> </td>
		    <td> <jsp:getProperty name="m"  property="pwd"  />  </td>
		    <td> <jsp:getProperty name="m"  property="name"  /> </td>
		    <td> <jsp:getProperty name="m"  property="email"  /> </td>
	   	</tr>
		<tr height="1" bgcolor="#99ccff">
	    	<td colspan="5"></td>
		</tr>
	</table>
</body>
</html>