<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	int score = Integer.parseInt(request.getParameter("score"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점수를 출력함</title>
</head>
<body>
	<h1>시험점수 <%= score %>점</h1>
<% 	
	if(score >= 90) {
%>
		<h1>a학점입니다.</h1>
<% 
	} else if(score >= 80 && score < 90) {
%>
		<h1>b학점입니다.</h1>
<% 	
	} else if(score >= 80 && score < 90) {
%>
		<h1>c학점입니다.</h1>
<% 	
	} else if(score >= 80 && score < 90) {
%>
		<h1>d학점입니다.</h1>
<% 	
	} else if(score >= 80 && score < 90) {
%>
		<h1>f학점입니다.</h1>
<% 
	}
%>
	<a href="../pro12.scoreTest.html">시험점수 입력</a>
</body>
</html>