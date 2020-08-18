<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="list">게시판</a>
<c:choose>
	<c:when test="${sessionScope.userId == null}">
		<a href="login">로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.userName }님이 로그인중입니다.
		<a href="${path}logout">로그아웃</a>
	</c:otherwise>
</c:choose>