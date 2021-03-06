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
				url:"${contextPath}/json3",
				success: function(data, textStatus) {
					var jsonInfo = JSON.parse(data);
					var memberInfo = "회원정보<br>"
					memberInfo += "============<br>";
					
					/* 배열이름 members로 회원정보를 출력 */
					for(var i in jsonInfo.members) {
						memberInfo += "이름 : " + jsonInfo.members[i].name + "<br>";
						memberInfo += "나이 : " + jsonInfo.members[i].age + "<br>";
						memberInfo += "성별 : " + jsonInfo.members[i].gender + "<br>";
						memberInfo += "별명 : " + jsonInfo.members[i].nickname + "<br>";
					}
					
					var booksInfo = "<br><br>도서정보<br>";
					booksInfo += "============<br>";
					
					/* 배열이름 books로 회원정보를 출력 */
					for(var i in jsonInfo.books) {
						booksInfo += "제목 : " + jsonInfo.books[i].title + "<br>";
						booksInfo += "저자 : " + jsonInfo.books[i].writer + "<br>";
						booksInfo += "가격 : " + jsonInfo.books[i].price + "<br>";
						booksInfo += "장르 : " + jsonInfo.books[i].genre + "<br>";
						/* 이미지URL을 구래 img태그의 src속성에 설정 */
						imageURL = jsonInfo.books[i].image;
			            booksInfo += "<img src="+imageURL+" />"+"<br><br><br>";
					}
					$("#output").html(memberInfo + "<br>" + booksInfo);
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
	<a id="checkJson" style="cursor:pointer">데이터 수신하기</a><br><br>
    <div id="output"></div>
</body>
</html>