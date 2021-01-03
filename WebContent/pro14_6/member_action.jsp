<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, pro14.sec02.ex01.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="pro14.sec02.ex01.MemberBean" />
<jsp:setProperty property="*" name="m"/>
<%
	MemberDAO memberDAO = new MemberDAO();
	memberDAO.addMember(m);
		// 회원정보를 추가
	List membersList = memberDAO.listMembers();
		// 회원정보를 조회
	request.setAttribute("membersList", membersList);
		// 조회된 회원정보를 request에 바인딩
%>
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="membersList.jsp" />
</body>
</html>