<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%!
	/* ������ �̿��� ��� ���� name�� ����޼��� getName()�� ������ */
	String name = "��ũ";
	public String getName() { return name;}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ����</title>
</head>
<body>
	<!-- ǥ������ �̿��� ���𹮿��� ������ name�� ���� ��� -->
	<h1>�ȳ��ϼ��� <%= name %>��.</h1>
</body>
</html>