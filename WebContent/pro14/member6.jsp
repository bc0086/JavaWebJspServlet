<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="pro14.sec01.ex01.MemberBean, java.util.ArrayList.*"
    isELIgnored="false"
    %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m" class="pro14.sec01.ex02.MemberBean" /> 
<jsp:setProperty name="m" property="*" />

<jsp:useBean id="addr" class="pro14.sec01.ex02.Address" />
<jsp:setProperty name="addr" property="city" value="서울" />
<jsp:setProperty name="addr" property="zipcode" value="07654" />

<!-- MemberBean의 addr속성에 Address빈을 설정함 -->
<%
	m.setAddr(addr);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력창</title>
</head>
<body>
	<table border="1"  align="center" >
		<tr align="center"  bgcolor="#99ccff" >
	      	<td width="7%"><b>아이디</b></td>
	      	<td width="7%"><b>비밀번호</b></td>
	      	<td width="5%" ><b>이름</b></td>
	      	<td width="5%"><b>이메일</b></td>
			<td width="5%" ><b>도시</b></td>
	      	<td width="5%" ><b>우편번호</b></td>
  		</tr>

		<!-- HashMap 이름 뒤에 마침표 연산자로 저장 시 사용한 key를 이용하여 value를 가져옴 -->
	   	<tr align=center>
	    	<td>${membersMap.id }</td>
	    	<td>${membersMap.pwd }</td>
	    	<td>${membersMap.name }</td>
	    	<td>${membersMap.email }</td>
	   	</tr>
	   	
	   	<!-- 표현식을 사용한 방법 -->
	   	<tr align="center">
	     	<td>${m.id } </td>
	      	<td>${m.pwd } </td>
	      	<td>${m.name } </td>
	      	<td>${m.email}</td>
	      	<td><%=m.getAddr().getCity() %></td>
	      	<td><%=m.getAddr().getZipcode() %></td>   
		</tr>
	   	
	   	<!-- 빈 속성을 사용한 방법 -->
	   	<tr align=center>
	    	<td>${m.id } </td>
	      	<td>${m.pwd } </td>
	      	<td>${m.name } </td>
	      	<td>${m.email }</td>
	      	<td>${m.addr.city }</td>
	      	<td>${m.addr.zipcode }</td>   
	   	</tr>
   </table>
</body>
</html>