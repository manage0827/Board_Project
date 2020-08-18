<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<%-- <%@ include file="../include/header.jsp" %> --%>
<script >

	 $(document).ready(function(){
		 var formObj = $("form");
		 
		 $('button').on("click", function(e){
			 
			 e.preventDefault();
			 
			 var operation = $(this).data("oper");
			 
			 console.log(operation);
			 
			 if(operation === 'remove'){
				 formObj.attr("action", "delete");
			 }else if(operation === 'list'){
				 //move to list
				 self.location = "list";
				 return ;
			 }
			 formObj.submit();
		 });
	});
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
<h2>게시글 보기</h2>
<form name="form1" method="post" action="update">
	<div>		<!-- 원하는 날짜형식으로 출력하기 위해 fmt 태그사용 -->
		작성일자 : <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd a HH:mm:ss" />
				<!-- 날짜 형식 => yyyy 4자리 연도, MM월, dd 일 , a 오전/오후 , HH 24시간 hh 12시간, mm 분, ss 초 -->
	</div>
	<div>
		조회수 : ${dto.viewcnt}
	</div>
	<div>
		제목
		<input name="title" id="title" size="80" value="${dto.title}" placeholder="제목을 입력해주세요">
	</div>
	<div>
		내용
		<textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요">${dto.content }</textarea>
	</div>
	<div>
		이름
		<input name="writer" id="writer" value="${dto.writer}" placeholder="이름을 입력해주세요">
	</div>
	<div style="width:650px; text-align:center;">
		<!-- 게시물번호를 hidden 으로 처리 -->
		 <input type="hidden" name="bno" value="${dto.bno}">
		<!-- <input type="submit" id="btnUpdate" value="수정" /> -->
		<c:if test="${sessionScope.userId == dto.writer }">
		<button type="submit" data-oper='modify' class="btn btn-default">수정</button>
		<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
		<button type="submit" data-oper='list' class="btn btn-default">목록으로</button>
		
		</c:if>
	</div>
</form>
</body>
</html>