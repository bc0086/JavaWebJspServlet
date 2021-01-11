<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
</head>
<body>
	<table border="0" align="center">
		<tr>
			<td width="150" align="center" bgcolor="#FF9933"> 글번호</td>
			<td>
				<input type="text" value="${article.articleNO }" />
				<input type="hid-den" name="articleNO" value="${article.articleNO }" />
			</td>
		</tr>
	</table>
</body>
</html>