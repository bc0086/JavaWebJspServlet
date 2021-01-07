<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$("#checkJson").click(function(){
			var _jsonInfo = '{"name":"박지성", "age":"25", "gender":"남자", "nickname":"날샌돌이"}';
			$.ajax({
				type: "post",
				async: false,
				url:"${contextPath}/json",
				data: {jsonInfo: _jsonInfo},
				success: function(data, textStatus) {
				},
				error:function(data, textStatus) {
					alert("에러발생");
				},
				complete:function(data, textStatus) {
				}
			}); // end ajax
		});
	})
</script>
</head>
<body>
	<a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>