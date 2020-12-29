<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// request 내장 객체에 바인딩
	request.setCharacterEncoding("utf-8");
	request.setAttribute("id", "hong");
	request.setAttribute("pwd", "1234");

	// session 내장 객체에 바인딩
	session.setAttribute("name", "홍길동");
	
	// application 내장 객체에 바인딩
	application.setAttribute("email", "hong@test.com");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward1</title>
</head>
<body>
	<!-- member1.jsp로 포워딩 -->
	<jsp:forward page="member1.jsp" />
</body>
</html>