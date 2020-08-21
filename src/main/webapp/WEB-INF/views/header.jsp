<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
.home {
	display: flex;
	justify-content: space-around;
	/*flex-basis:auto;*/
	margin: 10 0 10 0;
	align-items: center;
	width: 100%;
	height: 100px;
	max-width: none !important;
}

.logo {
	display: flex;
	align-items: center;
}

.member {
	display: flex;
	font-size: 16px;
}

.member a:hover {
	text-decoration: none;
	color: purple;
}

button {
	width: 70px;
	border-radius: 10px 10px;
	background-color: #42176d;
	border: none;
	color: #fff;
	padding: 2px 0;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 2px;
	cursor: pointer;
}
</style>
<body>
	<div>
		<!--헤더-->
		<header class="home">

			<div class="event" style="width: 250px">
				<a href="#">이벤트창</a>
			</div>

			<!--로고-->
			<a href="main.jsp">
				<!-- <img src="./image_main/logo.png" style="width:300px" > -->
			</a>

			<div class="member" style="width: 250px">

				<!--로그인버튼-->
				<button onclick="">Login</button>
				&nbsp;&nbsp;&nbsp;
				<!--회원가입버튼-->
				<button onclick="">Join Us</button>
			</div>
		</header>
	</div>
	</footer>
</body>
</html>