<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!-- HashMap으로 저장해서 넘어온 값들은 이름이 길어서 짧은 변수이름으로 재설정함 -->
<c:set var="articlesList" value="${articlesMap.articlesList }" />
<c:set var="totArticles" value="${articlesMap.totArticles }" />
<c:set var="section" value="${articlesMap.section }" />
<c:set var="pageNum" value="${articlesMap.pageNum }" />
<c:set var="noticeList" value="${articlesMap.noticeArticlesList }" />
<!DOCTYPE html>
<html>
<head>
<style>
   .no-uline {text-decoration:none;}
   .sel-page{text-decoration:none;color:red;}
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
</style>
<meta charset="UTF-8">
<title>글 목록창</title>
</head>
<body>
	<table align="center" border="1"  width="80%"  >
  		<tr height="10" align="center"  bgcolor="lightgreen">
	    	<td >글번호</td>
	     	<td >작성자</td>              
	     	<td >제목</td>
	     	<td >작성일</td>
  		</tr>
  		
  		<c:choose>
  			<c:when test="${articlesList == null }">
  				<tr height="10">
      				<td colspan="4">
         				<p align="center">
            				<b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        				</p>
				    </td>  
				</tr>
  			</c:when>
  			
  			<c:when test="${articlesList != null }">
  				<tr align="center">
  					<c:forEach var="article" items="${noticeList }" varStatus="articleNum">
	  					<!-- noticeList로 포워딩 된 글 목록을 forEach태그를 이용해 표시 -->
  						<tr align="center">
  							<td width="5%">${articleNum.count}</td>
	  							<!-- forEach태그의 varStatus의 count속성을 이용해 글 번호를 1부터 자동표시 -->
							<td width="10%">${article.id }</td>
							<td align='left'  width="35%">
	  							<span style="padding-right:30px"></span>
									<!-- 왼쪽으로 30px만큼 여백을 준 후 글 제목을 표시 -->
	  					
	  							<!-- 새 글 작성에 아이콘 추가 -->
		  						<c:choose>
		  							<c:when test="${article.level > 1 }">
		  								<c:forEach begin="1" end="${article.level }" step="1">
		  									<span style="padding-left:20px"></span>
		  								</c:forEach>
		  								<span style="font-size:12px;">[답변]</span>
		  								<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
		  								<img src="${contextPath }/image/ico_re.gif" alt="" />
		  							</c:when>
		  						
		  							<c:otherwise>
		  								<c:choose>
		  									<c:when test="${article.newArticle== true }">
		  										<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
		  										<img src="${contextPath }/image/ico_new.gif" alt="" />
		  									</c:when>
		  								
		  									<c:otherwise>
		  										<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
		  									</c:otherwise>
		  								</c:choose>
		  							</c:otherwise>
		  						</c:choose>
		  					</td>
	  				
		  					<td width="10%">
		  						<fmt:formatDate value="${article.writeDate }"/>
		  					</td>
	  					</tr>
  					</c:forEach>
  				</tr>
  				
				<!-- articleList로 포워딩 된 글 목록을 forEach태그를 이용해 표시 -->
				<c:forEach var="article" items="${articlesList }" varStatus="articleNum">
 					<tr align="center">
 						<td width="5%">${articleNum.count}</td>
  							<!-- forEach태그의 varStatus의 count속성을 이용해 글 번호를 1부터 자동표시 -->
						<td width="10%">${article.id }</td>
				
						<td align='left'  width="35%">
  							<span style="padding-right:30px"></span>
								<!-- 왼쪽으로 30px만큼 여백을 준 후 글 제목을 표시 -->
  					
  							<!-- 새 글 작성이 아이콘 추가 -->
  							<c:choose>
  								<c:when test="${article.level > 1 }">
  									<c:forEach begin="1" end="${article.level }" step="1">
  										<span style="padding-left:20px"></span>
  									</c:forEach>
  									<span style="font-size:12px;">[답변]</span>
  									<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
  									<img src="${contextPath }/image/ico_re.gif" alt="" />
  								</c:when>
  						
  								<c:otherwise>
  									<c:choose>
  										<c:when test="${article.newArticle== true }">
  											<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
  											<img src="${contextPath }/image/ico_new.gif" alt="" />
  										</c:when>
  								
  										<c:otherwise>
  											<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
  										</c:otherwise>
  									</c:choose>
  								</c:otherwise>
  							</c:choose>
  						</td>
  				
  						<td width="10%"><fmt:formatDate value="${article.writeDate }"/></td>
  					</tr>
 				</c:forEach>
  			</c:when>
  		</c:choose>
  	</table>
  	
  	<!-- 페이징 처리 -->
  	<div class="cls2">
  		<c:if test="${totArticles != null }"> <!-- 전체 글 수에 따라 페이징 표시를 다르게 함 -->
  			<c:choose>
  				<c:when test="${totArticles > 100 }">
  					<c:forEach var="page" begin="1" end="10" step="1">
  						<c:if test="${section>1 && page==1 }">
  							<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10+1}"> &nbsp; pre </a>
  						</c:if>
						  						
  						<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 + page }</a>
  						
  						<c:if test="${page ==10 }">
  							<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}"> &nbsp; next </a>
  						</c:if>
  					</c:forEach>
  				</c:when>
  				
  				<c:when test="${totArticles == 100 }">
  					<c:forEach var="page" begin="1" end="10" step="1">
						<a href="" class="no-uline">${page }</a>
  					</c:forEach>
  				</c:when>
  				
  				<c:when test="${totArticles < 100 }">
  					<c:forEach var="page" begin="1" end="${totArticles/10 + 1}" step="1">
  						<c:choose>
  							<c:when test="${page==pageNum }">
  								<a class="sel-page" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
  							</c:when>
  							
  							<c:otherwise>
								<a class="no-uline" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
  							</c:otherwise>
  						</c:choose>
  					</c:forEach>
  				</c:when>
  			</c:choose>
  		</c:if>
  	</div>
  	
  	<a class="cls1"  href="${contextPath }/board/articleForm.do">
  		<p class="cls2">글쓰기</p>
  	</a>
</body>
</html>