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
			location.href = "write";
		});
	});
	
	//원하는 페이지로 이동시 검색조건, 키워드값을 유지하기 위해
	function list(page){
		location.href="list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
	}
</script>
</head>
<body>
<%@ include file="menu.jsp" %> 
<h1>게시글 목록</h1>
<form name="form1" method="post" action="list">
	<select name="searchOption">
		<!-- 검색조건을 검색처리후 결과화면에 보여주기 위해 c:out 출력태그 사용, 삼항연산자 -->
		<option value="all" <c:out value="${map.searchOption == 'all'?'selected':' ' }"></c:out>>제목+이름+제목</option>
		<option value="userName" <c:out value="${map.searchOption == 'userName'?'selected':' ' }"></c:out>>이름</option>
		<option value="content" <c:out value="${map.searchOption == 'content'?'selected':' ' }"></c:out>>내용</option>
		<option value="title" <c:out value="${map.searchOption == 'title'?'selected':' ' }"></c:out>>제목</option>
	</select>
	<input name="keyword" value="${map.keyword }">
	<input type="submit" value="조회">
	
	<!-- 로그인한 사용자만 글쓰기 버튼을 활성화 -->
	<c:if test="${sessionScope.userId != null}">
		<button type="button" id="btnWrite">글쓰기</button>
	</c:if>
</form>
<!-- 레코드의 갯수를 출력 -->
${map.count}개의 게시물이 있습니다.
<table border="1" width="100%" >
	
	<tr>
		<th>NO</th>
		<th>글 제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	
	<c:forEach var="row" items="${map.list}">
	<tr>
		<td>${row.bno}</td>
		<!-- 게시글 상세보기 페이지로 이동시 게시글 목록페이지 있는 검색조건, 키워드, 현재 페이지값으 ㄹ유지 위해 -->
		<td><a href="view?bno=${row.bno}&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">${row.title}
		<!--  댓글이 있으면 게시글 이름 옆에 출력하기 -->
			<c:if test="${row.recnt>0}">
			<span style="color: red;">
			(${row.recnt })
			</span>
			</c:if>
		</a>
		</td>
		<td>${row.userName}</td>
		<td>
			<!--  원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 -->
			<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
		<td>${row.viewcnt}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<!--  처음페이지로 이동 : 현재 페이지가 1보다 크면 [처음]하이퍼링크를 화면에 출력 -->
			<c:if test="${map.boardPager.curBlock > 1}">
				<a href="javascript:list('1')">[처음]</a>
			</c:if>
			
			<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전] 하이퍼링크를 화면에 출력 -->
			<c:if test="${map.boardPager.curBlock > 1}">
				<a href="javascript:list('${map.boardPager.prevPage}')">[이전]</a>
			</c:if>
			<!-- 하나의 블럭에서 반복문 수행 시작페이지 부터 끝페이지까지 -->
			<c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
				<!-- 현재페이지이면 하이퍼 링크 제거 -->
				<c:choose>
					<c:when test="${num == map.boardPager.curPage }">
						<span style="color:red">${num}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="javascript:list('${num}')">${num}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
			<c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
				<a href="javascript:list('${map.BoardPager.nextPage}')">[다음]</a>
			</c:if>
			
			<!-- 끝페이지로 이동 : 현재페이지가 전체페이지보다 작거나 같으면 [끝]하이퍼링크 화면에 출력 -->
			<c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
				<a href="javascript:list('${map.boardPager.totPage}')">[끝]</a>
			</c:if>
			
			
</table>
</body>
</html>