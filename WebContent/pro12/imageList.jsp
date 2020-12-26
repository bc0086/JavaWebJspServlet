<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<style>
.lst_type{overflow:hidden;width:80%;padding:0 10px 10px; margin:0 auto}
.lst_type li{overflow:hidden;clear:both;margin:10px 0 0;color:#2d2c2d;
font-family:'돋움',Dotum;font-size:12px;line-height:100px;
list-style:none ; border-bottom: 2px solid lightgray;position:relative; }
.lst_type li img{display:inline;float:left;position:absolute; }
.lst_type li a{color:#2d2c2d;text-decoration:none; margin-left:340px}
.lst_type li a:hover{text-decoration:underline}
.lst_type li span{color:blue; margin-left:330px;font-family:'돋움',Dotum;font-size:14px;  }
</style>
<meta charset="EUC-KR">
<title>이미지 리스트창</title>
</head>
<body>
	<ul class='lst_type'>
		<!-- 리스트의 헤더를 표시 -->
		<li>
			<span style='margin-left:50px'>이미지</span>
			<span>이미지 이름</span>
			<span>선택하기</span>
		</li>
<%		
		/* for문을 이용하여 <li>태그를 연속해서 출력함 */
		for(int i=0; i<10; i++) {
%>
			<!-- <li>태그를 이용해 한 행에 <a>태그의 이미지와 텍스트를 나타냄 -->
			<li>
				<a href="#" style='margin-left:50px'>
					<!-- image 폴더의 이미지를 나타냄 -->
					<img src='../image/duke.png' width='90' height='90' alt="" />
				</a>
				<a href="#"><strong>이미지이름 : 듀크<%= i %></strong></a>
				<a href="#"><input name='chk<%= i %>' type='checkbox'></a>
			</li>
<%
		}
%>
	</ul>
</body>
</html>