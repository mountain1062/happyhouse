<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<script type="text/javascript">
	function findPassword() {
		if(document.getElementById("id").value == "") {
			alert("아이디를 입력하세요");
			return;
		} else if(document.getElementById("phone").value == "") {
			alert("휴대폰번호를 입력하세요");
			return;
		} else {
			document.getElementById("findPasswordFrom").action = "${root}/user.do";
			document.getElementById("findPasswordFrom").submit();
		}
	}
	
	function msgAlert() {
		alert("아이디, 휴대폰번호 확인해주세요");
	}
	</script>
</head>

<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>

	
	<div id = "findPassword">
	<c:if test="${error!=null}">
	<script type="text/javascript">msgAlert()</script>
	</c:if>
	<h2>비밀번호 찾기</h2>
	<c:if test="${ !empty msg }">
	<h3>비밀번호는 ${msg} 입니다</h3>
	</c:if>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="findPasswordFrom" method="post" action="">
			<input type="hidden" name="act" id="act" value="findPassword">
				<div class="form-group" align="left">
					<label for="">아이디</label>
					<input type="text" class="form-control" id="id" name="id" placeholder="">
				</div>
				<div class="form-group" align="left">
					<label for="">휴대폰번호</label>
					<input type="text" class="form-control" id="phone" name="phone" placeholder="" >
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-warning" onclick="javascript:findPassword();">비밀번호찾기</button>
				</div>
			</form>
		</div>
	</div>
	</div>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>