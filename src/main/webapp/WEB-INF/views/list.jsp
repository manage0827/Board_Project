<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 

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
<form name="form1" method="post" action="list">
	<select name="searchOption">
		<!-- 검색조건을 검색처리후 결과화면에 보여주기 위해 c:out 출력태그 사용, 삼항연산자 -->
		<option value="value" <c:out value="${map.searchOption == 'all'?'selected':' ' }"></c:out>>제목+이름+제목</option>
		<option value="writer" <c:out value="${map.searchOption == 'writer'?'selected':' ' }"></c:out>>이름</option>
		<option value="content" <c:out value="${map.searchOption == 'content'?'selected':' ' }"></c:out>>내용</option>
		<option value="title" <c:out value="${map.searchOption == 'title'?'selected':' ' }"></c:out>>제목</option>
	</select>
	<input name="keyword" value="${map.keyword }">
	<input type="submit" value="조회">
	
</form>
<!-- 레코드의 갯수를 출력 -->
${map.count }개의 게시물이 있습니다.
<a href="write" textalign="left"><p>글쓰기</p></a>
<table border="1" width="100%" >
	
	<tr>
		<th>NO</th>
		<th>글 제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	
	<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.bno}</td>
		<td><a href="view?bno=${row.bno}">${row.title}</a></td>
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