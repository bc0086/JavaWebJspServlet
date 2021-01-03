<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, pro14.sec02.ex01.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextName" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원검색창</title>
</head>
<body>
	<form method="post" action="member_action.jsp">
		<input type="hidden" name="command" value="search" />
	   	이름:<input type="text" name="name"><br>
	   	<input type ="submit" value="조회하기">
   </form>
   <a href="${contextName }/pro14_6/memberForm.jsp">회원 가입하기</a>
</body>
</html>