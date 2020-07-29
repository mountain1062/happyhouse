<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
</head>
<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>
<br>
	<h2>예기치 못한 에러가 발생했습니다.</h2>
	<div>${ msg }</div>
	<div>
		<a href="main.do">메인 화면으로 가기</a>
	</div>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>