<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	안녕하세요. 쇼핑몰 중심 JSP 시작입니다.
	<br />
	<!-- duke_image.jsp를 동적으로 포워딩 -->
	<!-- param 액션 태그를 이용해 duke_image.jsp로 이름과 파일이름을 전달 -->
	<jsp:include page="duke_image.jsp" flush="true">
		<jsp:param name="name" value="듀크" />
		<jsp:param name="imgName" value="duke.png" />
	</jsp:include>
	<br />
	안녕하세요. 쇼핑몰 중심 JSP 끝 부분입니다.
</body>
</html>