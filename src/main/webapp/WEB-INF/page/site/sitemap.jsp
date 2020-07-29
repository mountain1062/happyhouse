<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<c:set var="col" value="6"/>
<c:if test="${userinfo != null && userinfo.admin }">
	<c:set var="col" value="4"/>
</c:if>
</head>
<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>
<br>
	<h2>사이트맵</h2>
	<article>
		<div class="row col-md-12 col-sm-12">
			<div class="col-md-${col} col-sm-${col}">
				<h5>회원관리</h5>
				<ul class="sitemap">
					<c:if test="${userinfo == null}">
					<li><a href="user.do">로그인</a></li>
					<li><a href="user.do?act=mvSingUp">회원가입</a></li>
					<li><a href="">비밀번호찾기</a></li>
					</c:if>
					<c:if test="${userinfo != null}">
					<li><a href="user.do?">회원정보</a></li>
					<li><a href="user.do?act=mvWithdrawal">회원탈퇴</a></li>
					<li><a href="user.do?act=logout">로그아웃</a></li>
					</c:if>
					<li><a href="main.do?act=noticelist">공지사항</a></li>
				</ul>
			</div>
			<div class="col-md-${col} col-sm-${col}">
				<h5>부동산거래정보</h5>
				<ul class="sitemap">
					<li><a href="deal.do?act=search&chksearchtype=0">아파트 매매 거래</a></li>
					<li><a href="deal.do?act=search&chksearchtype=1">아파트 전월세 거래</a></li>
					<li><a href="deal.do?act=search&chksearchtype=2">다세대,주택 매매 거래</a></li>
					<li><a href="deal.do?act=search&chksearchtype=3">다세대,주택 전월세 거래</a></li>
				</ul>
			</div>
			<c:if test="${userinfo != null && userinfo.admin }">
			<div class="col-md-4 col-sm-4">
					<h5>관리자용</h5>
					<ul class="sitemap">
					<li><a href="admin.do?act=userList">회원리스트</a></li>
					</ul>
			</div>
			</c:if>
		</div>
	</article>	
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>