<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<%-- <%@ include file="../include/header.jsp" %> --%>
<script>
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
	 
	  $(document).ready(function(){
		  // 댓글 목록 불러오기
		  listReply();
		  //listReply2(); // Json 리턴방식
		
		// 댓글 쓰기 버튼 클릭 이벤트 (ajax로 처리)
		 $("#btnReply").click(function(){
			 var replytext = $("#replytext").val();
			 var bno="${dto.bno}"
			 // 비밀 댓글 체크 여부
			 var secretReply = "n";
			 // 태그.is(":속성") 체크여부 true/false
			 if( $("#secretReply").is(":checked") ){
				 secretReply = "y";
			 }
			 // alert(secretReply);
			 // 비밀댓글 파라미터 추가
			 var param="replytext="+replytext+"&bno="+bno+"&secretReply="+secretReply;
			 $.ajax({
				 type:"post",
				/*  url: "{path}/reply/insert", */
				 url:"reply/insert",
				 data :param,
				 success: function(){
					 alert("댓글이 등록되었습니다");
					 listReply("1");
				 }
			 });
			 });
		 }); 
	 
		
		 
		
		 // 목록 버튼 클릭 이벤트 : 버튼 클릭시 상세보기화면에 있던 페이지, 검색옵션, 키워드 값을 가지고 목록으로 이동
		 $('#btnList').click(function(){
			 location.href="${path}/board/list?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}";
		 });
		 
		// Controller 방식
		// 댓글 목록
		    function listReply(num){
			$.ajax({
				type:"post",
				url: "reply/replyList?bno=${dto.bno}&curPage="+num,
				success: function(result){
					//responseText 가 result에 저장됨
					$("#listReply").html(result);
				}
			});
		}   
	 	// RestController 방식(json)
	 	// 댓글 목록
	 	/* function listReply2(){
	 		$.ajax({
	 			type:"post",
	 			//contentType:"application/json", ==> 생략가능 (RestController 라서 가능)
	 			url: "reply/listJson?bno=${dto.bno}",
	 			success: function(result){
	 				console.log(result);
	 				var output ="<table>";
	 				for(var i in result){
	 					output += "<tr>";
	 					output += "<td>" + result[i].userName;
	 					output += "("+changeDate(result[i].regdate)+")<br>";
	 					output += result[i].replytext+"<td>";
	 					output += "<tr>";
	 				}
	 				output += "</table>";
	 				$("#listReply").html(output);
	 			}
	 		});
	 	}   */
		 // 날짜 변환 함수 작성
		 function changeDate(date){
			 date = new Date(parseInt(date));
			 year = date.getFullYear();
			 month = date.getMonth();
			 day = date.getDate();
			 hour = date.getHours();
			 minute = date.getMinutes();
			 second = date.getSeconds();
			 strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
			 return strDate;
			 }
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
		${dto.userName}
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
		<!-- 상세보기 화면에서 게시글 목록화면으로 이동 -->
			<button type="button" id="btnList">목록</button>
	</div>
</form>
	<div style="width:650px; text-align:center;">
		<br>
		<!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
		<c:if test="${sessionScope.userId != null }">
		<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요"></textarea>
		<br>
		<!-- 비밀 댓글 체크박스 -->
		<input type="checkbox" id="secretReply">비밀 댓글
		<button type="button" id="btnReply">댓글 작성</button>
		</c:if>
	</div>
	<!-- 댓글 목록 출현할 위치 -->
	<div id="listReply"></div>
</body>
</html>