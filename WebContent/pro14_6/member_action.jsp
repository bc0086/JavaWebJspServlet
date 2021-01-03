<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, pro14.sec02.ex01.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	String command = (String) request.getParameter("command");
	MemberDAO memDAO = new MemberDAO();
	List membersList = null;
	if(command.equals("addMember")){
%>
<jsp:useBean id="m" class="pro14.sec02.ex01.MemberBean" />
<jsp:setProperty property="*" name="m"/>
<%
		memDAO.addMember(m);
		membersList = memDAO.listMembers();
	} else if(command.equals("search")) {
		String name= request.getParameter("name");
		MemberBean memberBean = new MemberBean();
		memberBean.setName(name);
		membersList = memDAO.listMembers(memberBean); // 회원정보를 조회
	}
	request.setAttribute("membersList", membersList); // 조회된 회원정보를 request에 바인딩
%>
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="membersList.jsp" />
</body>
</html>