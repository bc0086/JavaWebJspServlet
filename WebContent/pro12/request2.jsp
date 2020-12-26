<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	/* 첫 번째 JSP페이지에서 포워딩한 request객체에서 getAttribute()를 이용해 정보를 저장 */
	String name=(String)request.getAttribute("name");
	String address=(String)request.getAttribute("address");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- 이전 JSP에서 전송된 정보를 출력 -->
	<h1>이름은 <%= name %> 입니다.</h1> <br />
	<h1>주소는 <%= address %> 입니다.</h1>
</body>
</html>