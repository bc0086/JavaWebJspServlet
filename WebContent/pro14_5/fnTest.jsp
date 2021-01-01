<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="title1" value="hello world!" />
	<c:set var="title2" value="쇼핑몰 중심 JSP입니다." />
	<c:set var="str1" value="중심" />
	<h2>여러가지 문자열 함수 기능</h2>
	title1 = "hello world" <br />
	title2 = "쇼핑몰 중심 JSP입니다." <br />
	str1 = "중심" <br /><br />
	
	<!-- 문자열 길이를 반환 -->
	fn:length(title1) = ${fn:length(title1) } <br />
	
	fn:toupp
</body>
</html>