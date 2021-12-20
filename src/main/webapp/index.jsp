<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 

 <html>
<head>
	<title>Home</title>
		<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
</head>

<a href="sample/openBoardList.do">게시판</a><br />
<script type="text/javascript">

	
	$(document).ready(function(){
		
		$("#logoutBtn").on("click", function(){
			location.href="sample/logout.do";
		
		});
		
		$("#userinfo").on("click", function(){
			location.href="registers/register.do";
	
		});
		
		$("#memberUpdateBtn").on("click", function(){
			location.href="registers/memberUpdateView.do";
		});
		
		$("#memberDeleteBtn").on("click", function(){
			location.href="/firstproject/registers/memberDeleteView.do";
		});
	
		
	})
	
</script>
<body>
	<form name='homeForm' method="post" action="sample/login.do">
		
	
	<c:if test="${member == null}">
			<div>
				<label for="userId"></label>
				<input type="text" id="userId" name="userId">
			</div>
			<div>
				<label for="userPass"></label>
				<input type="password" id="userPass" name="userPass">
			</div>
			<div>
				<button type="submit">로그인</button>
				<button type="button" id ="userinfo">회원가입</button>
			</div>
		</c:if>
		
		
		<c:if test="${member != null }">
		<!--  
		<c:set var = "salary" scope = "session" value = "${0}"/>
			-->
			<div>
				<p>${member.userId}님 환영 합니다.</p>
				<button id="memberUpdateBtn" type="button">회원정보수정</button>
				<button id="logoutBtn" type="button">로그아웃</button>
				<button id="memberDeleteBtn" type="button">회원탈퇴</button>
			</div>
		</c:if>
		
		<c:if test="${msg eq 'fail'}">
			<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
		</c:if>
			
		<!--
			<c:set var = "salary" scope = "session" value = "${1}"/>
		-->	
	</form>
</body>





</html>


