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
	<c:if test="${userinfo == null}">
		<div id = "login">
			<%@ include file="/WEB-INF/page/user/login.jsp" %>
		</div>
	</c:if>
	
	<c:if test="${userinfo != null}">
		<div id = "userInfo">
			<h3>${msg}</h3>
			<%@ include file="/WEB-INF/page/user/userInfo.jsp" %>
		</div>
	</c:if>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>