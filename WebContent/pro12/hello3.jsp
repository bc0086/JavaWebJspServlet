<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%! 
	String name = "이순신";
	public String getName(){ return name;}
%>

<% String age = request.getParameter("age"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>표현식 연습</title>
</head>
<body>
	<!-- 표현식 안의 자바변수나 자바 식에는 세미콜론(;)이 있으면 안된다. -->
	<h1>안녕하세요 <%= name %>님</h1>
	<h1>나이는 <%= age %>입니다.</h1>
	<h1>키는 <%= 180 %>cm 입니다.</h1>
	<h1>나이 + 10은 <%= Integer.parseInt(age) + 10 %>살 입니다.</h1>
</body>
</html>