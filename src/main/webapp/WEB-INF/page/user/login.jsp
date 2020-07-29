<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${cookie.cookie_id.value != null}">
	<c:set var="saveid" value="${cookie.cookie_id.value}"/>
	<c:set var="saveCheck" value=" checked=\"checked\""/>
</c:if>
	<script type="text/javascript">
	function login() {
		if(document.getElementById("id").value == "") {
			alert("아이디를 입력하세요");
			return;
		} else if(document.getElementById("password").value == "") {
			alert("비밀번호를 입력하세요");
			return;
		} else {
			document.getElementById("loginform").action = "${root}/user.do?login";
			document.getElementById("loginform").submit();
		}
	}
	
	function findPassword() {
		document.location.href = "${root}/user.do?act=mvFindPassword";
	}
	
	function msgAlert() {
		alert("아이디, 비밀번호를 확인해주세요");
	}
	</script>
</head>

<body>
	<c:if test="${error!=null}">
	<script type="text/javascript">msgAlert()</script>
	</c:if>
	<h2>login</h2>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="loginform" method="post" action="">
			<input type="hidden" name="act" id="act" value="login">
				<div class="form-group" align="left">
					<label for="">아이디</label>
					<input type="text" class="form-control" id="id" name="id" placeholder="" value="${saveid}">
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호</label>
					<input type="password" class="form-control" id="password" name="password" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}">
				</div>
				<div class="form-group form-check" align="right">
				    <label class="form-check-label">
				      <input class="form-check-input" type="checkbox" id="saveCheck" name="saveCheck" value="saveok"${saveCheck}> 아이디저장
				    </label>
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-warning" onclick="javascript:login();">로그인</button>
					<button type="button" class="btn btn-primary" onclick="javascript:findPassword();">비밀번호찾기</button>
				</div>
			</form>
		</div>
	</div>

