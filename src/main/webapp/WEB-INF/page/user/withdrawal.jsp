<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>

	<script type="text/javascript">
	function withdrawal() {
		if(document.getElementById("id").value == "") {
			alert("아이디를 입력하세요");
			return;
		} else if(document.getElementById("password").value == "") {
			alert("비밀번호를 입력하세요");
			return;
		} else {
			document.getElementById("withdrawalForm").action = "${root}/user.do";
			document.getElementById("withdrawalForm").submit();
		}
	}
	
	function msgAlert() {
		alert("비밀번호를 확인해주세요");
	}
	</script>
</head>

<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/page/common/menu.jsp" %>
	<div id = "withdrawal">
	<c:if test="${error!=null}">
	<script type="text/javascript">msgAlert()</script>
	</c:if>
	<h2>회원탈퇴</h2>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="withdrawalForm" method="post" action="">
			<input type="hidden" name="act" id="act" value="withdrawal" >
				<div class="form-group" align="left">
					<label for="">아이디</label>
					<input type="text" class="form-control" id="id" name="id" placeholder="" value="${userinfo.id}" readonly>
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호</label>
					<input type="password" class="form-control" id="password" name="password" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}">
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-warning" onclick="javascript:withdrawal();">탈퇴하기</button>
				</div>
			</form>
		</div>
	</div>
	</div>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>