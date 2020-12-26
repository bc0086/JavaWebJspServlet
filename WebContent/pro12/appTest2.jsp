<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	String name=(String)session.getAttribute("name");
	String address=(String)session.getAttribute("address");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내장 객체 스코프 테스트2</title>
</head>
<body>
	<h1>이름은 <%= name %> 입니다.</h1> <br />
	<h1>주소는 <%= address %> 입니다.</h1>
</body>
</html>