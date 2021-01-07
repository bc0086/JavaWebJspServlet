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
			$.ajax({
				type: "post",
				async: false,
				url:"${contextPath}/json2",
				success: function(data, textStatus) {
					var jsonInfo = JSON.parse(data);
					var memberInfo = "회원정보<br>"
					memberInfo += "============<br>";
					
					for(var i in jsonInfo.members) {
						memberInfo += "이름 : " + jsonInfo.members[i].name + "<br>";
						memberInfo += "나이 : " + jsonInfo.members[i].age + "<br>";
						memberInfo += "성별 : " + jsonInfo.members[i].gender + "<br>";
						memberInfo += "별명 : " + jsonInfo.members[i].nickname + "<br>";
					}
					$("#output").html(memberInfo);
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