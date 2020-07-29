<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/page/common/loginModal.jsp" %>


<header>
	<hgroup>
		<h1 class="main-logo">Happy House</h1>
		<c:if test="${userinfo != null }">
			<div id="loginOK" style="text-align:right">
				<font style="font-weight: bold;">환영합니다, ${ userinfo.name }님</font>
				<button class="btn btn-danger" style="display:inline-block" onclick="location.href='user.do?act=logout'; alert('로그아웃되었습니다.');">로그아웃</button> 
				<button class="btn btn-primary" style="display:inline-block" onclick="location.href='user.do?act=confirm'">회원정보</button> 
			</div>
			
		</c:if>
		<c:if test="${userinfo == null }">
			<div class="loginNO" style="text-align:right">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal" style="display:inline-block">로그인</button> 
				<button class="btn btn-primary" style="display:inline-block" onclick="location.href='user.do?act=mvSingUp'">회원가입</button> 
			</div>
		</c:if>
	</hgroup>
	<nav class="navbar navbar-expand-sm bg-light">
		<!-- Links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="deal.do">부동산거래검색</a></li>
			<li class="nav-item"><a class="nav-link" href="main.do?act=noticelist">공지사항</a></li>
	
			<c:if test="${userinfo != null}">
				<!-- 회원탈퇴 정보메뉴로 이동 -->
				<c:if test="${userinfo.admin}">
					<li class="nav-item"><a class="nav-link" href="admin.do?act=userList">회원리스트</a></li>
				</c:if>
			</c:if>
			<li class="nav-item"><a class="nav-link" href="main.do?act=mvSitemap">사이트맵</a></li>
		</ul>
	</nav>
</header>