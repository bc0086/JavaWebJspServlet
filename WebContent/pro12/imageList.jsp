<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<style>
.lst_type{overflow:hidden;width:80%;padding:0 10px 10px; margin:0 auto}
.lst_type li{overflow:hidden;clear:both;margin:10px 0 0;color:#2d2c2d;
font-family:'����',Dotum;font-size:12px;line-height:100px;
list-style:none ; border-bottom: 2px solid lightgray;position:relative; }
.lst_type li img{display:inline;float:left;position:absolute; }
.lst_type li a{color:#2d2c2d;text-decoration:none; margin-left:340px}
.lst_type li a:hover{text-decoration:underline}
.lst_type li span{color:blue; margin-left:330px;font-family:'����',Dotum;font-size:14px;  }
</style>
<meta charset="EUC-KR">
<title>�̹��� ����Ʈâ</title>
</head>
<body>
	<ul class='lst_type'>
		<!-- ����Ʈ�� ����� ǥ�� -->
		<li>
			<span style='margin-left:50px'>�̹���</span>
			<span>�̹��� �̸�</span>
			<span>�����ϱ�</span>
		</li>
<%		
		/* for���� �̿��Ͽ� <li>�±׸� �����ؼ� ����� */
		for(int i=0; i<10; i++) {
%>
			<!-- <li>�±׸� �̿��� �� �࿡ <a>�±��� �̹����� �ؽ�Ʈ�� ��Ÿ�� -->
			<li>
				<a href="#" style='margin-left:50px'>
					<!-- image ������ �̹����� ��Ÿ�� -->
					<img src='../image/duke.png' width='90' height='90' alt="" />
				</a>
				<a href="#"><strong>�̹����̸� : ��ũ<%= i %></strong></a>
				<a href="#"><input name='chk<%= i %>' type='checkbox'></a>
			</li>
<%
		}
%>
	</ul>
</body>
</html>