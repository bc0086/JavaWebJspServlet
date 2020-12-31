<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 점수 입력페이지</title>
</head>
<body>
	<h1>시험 점수를 입력해주세요</h1>
	<form action="scoreResult.jsp" method="post">
		시험점수 : <input type="text" name="score" /> <br />
		<input type="submit" value="학점변환" />
	</form>
</body>
</html>