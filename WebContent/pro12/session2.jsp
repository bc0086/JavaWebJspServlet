<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	String name=(String)session.getAttribute("name");
	String address=(String)session.getAttribute("address");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>session ���� ��ü �׽�Ʈ2</title>
</head>
<body>
	�̸��� <%= name %> �Դϴ�. <br />
	�ּҴ� <%= address %> �Դϴ�. <br />
</body>
</html>