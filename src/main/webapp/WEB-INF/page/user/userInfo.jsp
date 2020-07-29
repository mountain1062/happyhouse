<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${cookie.cookie_id.value != null}">
	<c:set var="saveid" value="${cookie.ssafy_id.value}"/>
	<c:set var="saveCheck" value=" checked=\"checked\""/>
</c:if>
	<script type="text/javascript">
		function update() {
			if(document.getElementById("password").value == "") {
				alert("비밀번호를 입력하세요");
				return;
			} else {
				alert("수정완료");
				document.getElementById("userInfoFrom").action = "${root}/user.do";
				document.getElementById("userInfoFrom").submit();
			}
		}
			 
		function confirm() {
			document.location.href = "${root}/main.do";
		}	
		
		function mvWithdrawal() {
			document.location.href = "${root}/user.do?act=mvWithdrawal";
		}	
	</script>
</head>

<body>
	<h2>회원정보</h2>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<form id="userInfoFrom" method="post" action="">
			<input type="hidden" name="act" id="act" value="update">
				<div class="form-group" align="left">
					<label for="">아이디</label>
					<input type="text" class="form-control" id="id" name="id" placeholder="" value="${userinfo.id}" readonly>
				</div>
				<div class="form-group" align="left">
					<label for="">비밀번호</label>
					<input type="password" class="form-control" id="password" name="password" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}">
				</div>
				<div class="form-group" align="left">
					<label for="">이름</label>
					<input type="text" class="form-control" id="name" name="name" placeholder="" value="${userinfo.name}">
				</div>
				<div class="form-group" align="left">
					<label for="">주소</label>
					<input type="text" class="form-control" id="address" name="address" placeholder="" value="${userinfo.address}">
				</div>
				<div class="form-group" align="left">
					<label for="">휴대폰번호</label>
					<input type="text" class="form-control" id="phone" name="phone" placeholder="" value="${userinfo.phone}">
				</div>
				<div class="form-group" align="center">
					<button type="button" class="btn btn-warning" onclick="javascript:update();">수정</button>
					<button type="button" class="btn btn-primary" onclick="javascript:confirm();">확인</button>
					<button type="button" class="btn btn-secondary" onclick="javascript:mvWithdrawal();">회원탈퇴</button>
				</div>
			</form>
		</div>
	</div>
