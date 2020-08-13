<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록<title>

<%@ include file="header.jsp" %> 
<script>
	$(document).ready(function(){
		$("#btnWrite").click(function(){
			//페이지 주소 변경(이동)
			location.href = "${path}/write";
		});
	});
</script>
</head>
<body>
<%@ include file="menu.jsp" %> 
<h1>게시글 목록</h1>
<a href="write" textalign="left"><p>글쓰기</p></a>
<table border="1" width="100%">

	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	
	<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.bno}</td>
		<td>${row.title}</td>
		<td>${row.writer}</td>
		<td>
			<!--  원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 -->
			<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
		<td>${row.viewcnt}</td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>