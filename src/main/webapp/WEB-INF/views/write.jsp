<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<%@ include file="header.jsp" %>
<script>
	$(document).ready(function(){
		$("#btnSave").click(function(){
			//var title = document.form1.title.value; ==> name 속성으로 처리할 경우
			//var content = doucment.form1.content.value;
			//var writer = document.form1.writer.value;
			var title = $("#title").val();
			var content = $("#content").val();
			/* var writer = $("#writer").val(); */
			if(title == ""){
				alert("제목을 입력하세요");
				document.form1.title.focus();
				return ;
			}
			if(content == ""){
				alert("내용을 입력하세요");
				document.form1.content.focus();
				return ;
			}
		/* 	if(writer == ""){
				alert("이름을 입력하세요");
				document.form1.writer.focus();
				return ;
			} */
			// 폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
		});
	});
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
<h2>게시글 작성</h2>
<form name="form1" method="post" action="insert">
	<div>
		제목
		<input name="title" id="title" size="80" placeholder="제목을 입력해주세요">
		<p>
	</div>
	<div>
		내용
		<textarea name="content" id="content" rows="8" cols="100" placeholder="내용을 입력해주세요"></textarea>
		<p>
	</div>
	<!-- <div>
		이름
		<input name="writer" id="writer" placeholder="이름을 입력해주세요">
		<p>
	</div> -->
	<div style="width:650px; text-align:center">
		<input id="btnSave" type="submit"  value="확인" />
		<input type="reset" value="취소" />
		</div>
		
		
</form>
</body>
</html>