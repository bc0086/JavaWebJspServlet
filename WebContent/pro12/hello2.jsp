<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%! 
	String name = "이순신";
	public String getName(){ return name;}
%>

<!-- 스크립트릿안에는 자바 코드만 사용이 가능하다 -->
<% String age = request.getParameter("age"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>스크립트릿 연습</title>
</head>
<body>
	<h1>안녕하세요 <%= name %>님</h1>
	<h1>나이는 <%= age %>입니다.</h1>
</body>
</html>