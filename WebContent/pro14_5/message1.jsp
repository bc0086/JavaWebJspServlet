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
<title>JSTL 다국어 기능</title>
</head>
<body>
	<%-- <fmt:setLocale value="ko_KR"/> --%>
	<fmt:setLocale value="en_US"/>

	<h1>
		회원정보 <br /><br />
		
		<!-- resource패키지 아래 member프로퍼티 파일을 읽어옴 -->
		<fmt:bundle basename="resource.member">
		
			<!-- fmt:message태그의 key 속성에 프로퍼티 파일의 key를 지정하여 값을 출력 -->
			이름 <fmt:message key="mem.name" /> <br />
			주소 <fmt:message key="mem.address" /> <br />
			직업 <fmt:message key="mem.job" /> <br />
		</fmt:bundle>
	</h1>
</body>
</html>