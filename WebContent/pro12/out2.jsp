<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String age=request.getParameter("age");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>데이터 출력 창</title>
</head>
<body>
<% 
	if(name != null || name.length() != 0) {
%>
		<!-- 1번 방법 : 표현식으로 출력 -->
		<h1><%= name %>, <%= age %> </h1>
<% 
	} else {
%>
		<h1>이름을 입력하세요</h1>
<%
	}

	/* 2번 방법 : 내장객체로 출력 */
	if(name != null || name.length() != 0) {
%>
		<h1><% out.println(name +", "+ age); %></h1>
<%
	} else {
%>
		<h1>이름을 입력하세요</h1>
<%
	}
%>
</body>
</html>