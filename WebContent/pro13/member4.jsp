<%@page import="org.apache.catalina.tribes.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*, pro13.sec01.ex01.*"
    pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!-- 2. useBean액션 태그로 id가 m인 MemberBean객체를 생성함 -->
<jsp:useBean id="m" class="pro13.sec01.ex01.MemberBean" scope="page" />

<!-- 3. 회원가입창에서 전송된 동일한 이름의 매개변수에 해당되는 유즈빈 속성에 전송된 값을 설정 -->
<!-- 4. 회원가입창에서 전달된 매개변수 이름과 속성이름이 같으면 같은 이름으로 값을 설정 -->
<jsp:setProperty name="m" property="id" param="id" />
<jsp:setProperty name="m" property="pwd" param="pwd" />
<jsp:setProperty name="m" property="name" param=" name" />
<jsp:setProperty name="m" property="email" param="email" />
<%
	MemberDAO memberDAO = new MemberDAO();
	memberDAO.addMember(m); // 회원 정보를 테이블에 추가
	
	List membersList = memberDAO.listMembers(); // 전체 회원 정보를 조회
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원 목록창</title>
</head>
<body>
	<table align="center"  width="100%">
		<tr align="center" bgcolor="#99ccff">
	    	<td width="7%" >아이디</td>
	      	<td width="7%">비밀번호</td>
	      	<td width="5%" >이름</td>
	      	<td width="11%" >이메일</td>
	      	<td width="5%" >가입일</td>
	   	</tr>
<% 	   	
	   	if(membersList.size() == 0) {
%>
			<tr>
				<td colspan="5">
					<p align="center">
						<b><span style="font-size:9pt;">등록된 회원이  없습니다.</span></b>
					</p>
				</td>
			</tr>
<%	   	
	   	} else {
	   		/* for반복문을 통해 membersList의 저장된 MemberBean객체를 한 개씩 가져온 후 
	   			각 속성의 회원 정보를 다시 getter를 이용해 출력한다 */
	   		for(int i = 0; i < membersList.size(); i++) {
	   			MemberBean bean = (MemberBean) membersList.get(i);
%>
			<tr align="center">
	       		<td><%=bean.getId() %></td>
	       		<td><%=bean.getPwd() %></td>
	       		<td><%=bean.getName() %></td>
	       		<td><%=bean.getEmail() %></td>
	       		<td><%=bean.getJoinDate() %></td>
	   		</tr>
<% 
	   		}
	   	}
%>
		<tr height="1" bgcolor="#99ccff">
	    	<td colspan="5"></td>
		</tr>
	</table>
</body>
</html>