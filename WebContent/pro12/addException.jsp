<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>에러 페이지</title>
</head>
<body>
	===== toString() 내용 ===== <br />
	<h1><%= exception.toString() %></h1>
		<!-- exception 내장객체를 사용해 예외처리를 함. -->
	
	===== getMessage() 내용 ===== <br />
	<h1><%= exception.getMessage() %></h1>
	
	===== printStackTrace() 내용 ===== <br />
	<h1><% exception.printStackTrace(); %></h1>
		<!-- 이클립스 콘솔로 예외메시지를 출력함. -->	
		
	<h3>
		숫자만 입력 가능합니다. 다시 시도하세요.
		<a href="../pro12.add.html">다시하기</a>
	</h3>
</body>
</html>