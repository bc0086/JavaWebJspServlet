<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포매팅 태그 라이브러리 예제</title>
</head>
<body>
	<h2>fmt의 number태그를 이용한 숫자 포맷팅 예제</h2>\
	<c:set var="price" value="1000000" />
	<fmt:formatNumber value="${price }" type="number" var="priceNumber" />
	
	통화로 표현 시 : 
	<fmt:formatNumber type="currency" currencySymbol="￦" value="${price}" groupingUsed="true"/><br>

	퍼센트로 표현 시 :
	<fmt:formatNumber type="percent" value="${price }"/>
</body>
</html>