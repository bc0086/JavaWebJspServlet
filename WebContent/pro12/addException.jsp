<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ������</title>
</head>
<body>
	===== toString() ���� ===== <br />
	<h1><%= exception.toString() %></h1>
		<!-- exception ���尴ü�� ����� ����ó���� ��. -->
	
	===== getMessage() ���� ===== <br />
	<h1><%= exception.getMessage() %></h1>
	
	===== printStackTrace() ���� ===== <br />
	<h1><% exception.printStackTrace(); %></h1>
		<!-- ��Ŭ���� �ַܼ� ���ܸ޽����� �����. -->	
		
	<h3>
		���ڸ� �Է� �����մϴ�. �ٽ� �õ��ϼ���.
		<a href="../pro12.add.html">�ٽ��ϱ�</a>
	</h3>
</body>
</html>