<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="result.jsp">
		아이디 : <input type="text" size=20 /><br>
	   비밀번호: <input  type="password"  size=20 /><br>
	   <input  type="submit" value="로그인" /> <input type="reset" value="다시입력"  />
	</form> <br><br>
	
	<!-- <a href="http://localhost:8090/pro14/test01/memberForm.jsp">회원가입하기</a> -->
			<!-- 직접 컨텍스트 이름을 입력해 요청 -->
   
    <%-- <a href="<%=request.getContextPath() %>/test01/memberForm.jsp">회원가입하기</a> --%>
    		<!-- request의 getContextPath()메서드를 이용해 컨텍스트 이름을 가져옴 -->
	
	<a href="${pageContext.request.contextPath}/pro14/memberForm.jsp">회원가입하기</a>
		<!-- 자바코드 없이 pageContext의 속성인 request하위의 contextPath 속성으로 컨텍스트 이름을 가져옴 -->
</body>
</html>