<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="pro14.sec01.ex01.*"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	MemberBean member = new MemberBean("lee","1234","이순신","lee@test.com");
	
	// 속성 이름 member로 MemberBean객체를 바인딩함
	session.setAttribute("member", member);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward2</title>
</head>
<body>
	<jsp:forward page="member8.jsp" />
</body>
</html>