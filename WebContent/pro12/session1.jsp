<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	String name=(String)session.getAttribute("name");
	session.setAttribute("address", "����� ������");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>session ���� ��ü �׽�Ʈ</title>
</head>
<body>
	�̸��� <%= name %> �Դϴ�. <br />
	<a href="../pro12/session2.jsp">�ι�° �������� �̵�</a>
</body>
</html>