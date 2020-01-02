<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl의 core 기능 사용을 위한 태그 라이브러리 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
	padding-left: 5px;
	padding-right: 5px;
}
li{
	list-style: none;
}
</style>
<title>Spring MVC Board</title>
<!-- 화면 출력 크기 설정 -->
<!-- 너비는 화면에 가득 차게 기본 비율은 1배 최대 비율은 1배 확대축소 안되게 설정 -->
<!-- 모바일 페이지에서 주로 설정 -->
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>

<!-- 부트스트랩 적용을 위한 css 설정 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<!-- IE9 버전 이하의 브라우저에서 HTML5를 적용하기 위한 설정 -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.comrespond/1.4.2respond.min.js"></script>
<![endif ]-->
</head>

<!-- jquery 설정 -->
<script src="/resources/jquery/jquery.js"></script>

<!-- class 와 role 속성을 이용해서 부트스트랩 적용 -->
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<div class="page-header">
				<h1>Spring MVC 게시판</h1>
				<!-- 로그인이 된 경우 -->
				<c:if test="${user != null}">
					<li role="presentation" align="right"><a href="#"> 
					<span class="badge">
					<img src="/userimage/${user.image}"	width="30" height="20" />
					</span>&nbsp;${user.nickname}님
					</a></li>
				</c:if>
			</div>
		</header>
	</div>
	<aside class="main-sidebar">
		<section class="sidebar">
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="/">메인</a></li>
				
				<!-- 로그인이 되지 않은 경우 -->
				<c:if test="${user == null}">
					<li role="presentation"><a href="/user/join">회원가입</a></li>
				</c:if>
				<!-- 로그인이 된 경우 -->
				<c:if test="${user != null}">
					<li role="presentation"><a href="/board/write">게시물 쓰기</a></li>
				</c:if>

				<li role="presentation"><a href="/board/list">게시물 목록보기 </a></li>
				<li role="presentation"><a href="/item/fruit">상품목록출력 </a></li>
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"> Spring 다양한 출력<span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">파일목록 출력</a></li>
						<li><a href="#">EXCEL 출력</a></li>
						<li><a href="#">PDF 출력</a></li>
						<li><a href="#">JSON 출력</a></li>
						<li><a href="#">CSV 출력</a></li>
						<li class="divider"></li>
						<li><a href="#">나중에 구현할꺼임</a></li>
					</ul></li>
			</ul>
		</section>
	</aside>
	<div>