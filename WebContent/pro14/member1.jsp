<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
    
 	<!-- 표현식으로 표현하기 위해 getParameter() 메서드를 이용해 회원정보를 가져옴 -->
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1"  align="center" >
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
	      	<td width="20%"><b>비밀번호</b></td>
	      	<td width="20%" ><b>이름</b></td>
	      	<td width="20%"><b>이메일</b></td>
	   	</tr>
	   
	   	<!-- getParameter()로 가져온 회원정보를 표현식으로 출력함 -->
	   	<tr align=center>
	    	<td><%= id %></td>
	    	<td><%= pwd %></td>
	    	<td><%= name %></td>
	    	<td><%= email %></td>
	   	</tr>
	   	
	   	<!-- param객체를 이용해 getParameter()메서드를 이용하지 않고 바로 회원정보를 출력함 -->
	   	<tr align=center>
	    	<td>${param.id }</td>
	    	<td>${param.pwd }</td>
	    	<td>${param.name }</td>
	    	<td>${param.email }</td>
	   	</tr>
   </table>
</body>
</html>