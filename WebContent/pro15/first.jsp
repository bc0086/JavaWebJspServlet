<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="result.jsp" method="post">
	<!-- 다운로드 할 파일이름을 매개변수로 전달 -->
	<input type="hid-den" name="param1" value="duke.png" /> <br />
	<input type="hid-den" name="param2" value="duke2.png" /> <br />
	<input type="submit" value="이미지 다운로드" /> <br />
</form>	
</body>
</html>