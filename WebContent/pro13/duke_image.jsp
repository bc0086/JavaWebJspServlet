<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
	/* param �׼� �±׷� ���޵� �Ű������� getParameter()�޼��带 �̿��� ������ */
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String imgName = request.getParameter("imgName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ũ�̹���</title>
</head>
<body>
	<br /><br />
	<h1>�̸��� <%= name %>�Դϴ�.</h1><br /><br />
	<img src="../image/<%= imgName %>" alt="" />
</body>
</html>