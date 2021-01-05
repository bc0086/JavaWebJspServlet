<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$("#checkJson").click(function(){
			var jsonStr = '{"name":["홍길동", "이순신", "임꺽정"]}';
				/* 이름을 저장하는 JSON배열을 name으로 전환함 */
			var jsonObj = JSON.parse(jsonStr);
				/* 제이쿼리의 JSON기능인 parse()메서드를 이용해 JSON자료형을 가져옴 */
			var output = "회원 이름" <br />;
		});
	});
</script>
</head>
<body>

</body>
</html>