<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="pro14.sec01.ex01.*, java.util.*"
    isELIgnored="false"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// ArrayList 객체를 생성
	List membersList = new ArrayList();
	
	// MemberBean 객체를 생성한 후 두 명의 회원정보를 저장
	MemberBean m1 = new MemberBean("lee","1234","이순신","lee@test.com");
	MemberBean m2 = new MemberBean("son","1234","손흥민","son@test.com");
	
	// 두개의 MemberBean객체를 ArrayList에 저장
	membersList.add(m1);
	membersList.add(m2);
	
	// request 내장 객체에 ArrayList를 속성이름 memberList로 바인딩함
	request.setAttribute("membersList", membersList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward3</title>
</head>
<body>
	<jsp:forward page="member9.jsp" />
</body>
</html>