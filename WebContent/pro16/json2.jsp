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
			/* 정수형 데이터를 가지는 이름이 age인 배열을 선언 */
			var jsonStr = '{"age":[22,33,44]}';
			
			/* parse() 메서드로 배열을 구함 */
			var jsonInfo = JSON.parse(jsonStr);
			var output = "회원나이 <br>";
			output += "==========<br>";
			
			/* 배여 ㄹ요소(나이)를 차례대로 출력함. */
			for(var i in jsonInfo.age) {
				output += jsonInfo.age[i] + "<br>";
			}
			$("#output").html(output);
		});
	});
</script>
</head>
<body>
	<a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>