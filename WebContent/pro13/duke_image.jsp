<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
	/* param 액션 태그로 전달된 매개변수를 getParameter()메서드를 이용해 가져옴 */
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String imgName = request.getParameter("imgName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>듀크이미지</title>
</head>
<body>
	<br /><br />
	<h1>이름은 <%= name %>입니다.</h1><br /><br />
	<img src="../image/<%= imgName %>" alt="" />
</body>
</html>