<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력창</title>
</head>
<body>
	<table align="center" border=”1” align="center" >
   		<tr align="center" bgcolor="lightgreen" >
      		<td width="7%" ><b>아이디</b></td>
		    <td width="7%" ><b>비밀번호</b></td>
		    <td width="7%" ><b>이름</b></td>
		    <td width="7%"><b>이메일</b></td>
		    <td width="7%" ><b>가입일</b></td>
   		</tr>
	
		<c:choose>
			<!-- 표현언어에서 바인딩 된 속성이름으로 바로 접근 -->
			<c:when test="${membersList==null }">
				<tr>
					<td colspan=5><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			
			<c:when test="${membersList!=null }">
				<!-- membersList에 저장된 memberBean을 mem에 차례대로 가져와 속성이름으로 출력 -->
				<c:forEach var="mem" items="${membersList }">
					<tr align="center">
						<td>${mem.id }</td>
						<td>${mem.pwd }</td>
						<td>${mem.name }</td>
						<td>${mem.email }</td>
						<td>
							<!-- 날짜 표현방법 변경 -->
							<fmt:formatDate value="${mem.joinDate }" pattern="dd-MM-YYYY" />
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>  
</body>
</html>