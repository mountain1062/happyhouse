<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		$("#id").change(function() {
			$("id_chk_res").text("");
			$("id_chk_res").removeClass("text-primary");
			$("id_chk_res").removeClass("text-danger");
			if($("#id").val()==""){
				return;
			}
			var idReg = /^[A-Za-z0-9]{5,10}$/g;
			if(!idReg.test($("#id").val())){
				$("#id_chk_res").text("사용불가 아이디");
				$("#id_chk_res").toggleClass("text-danger");
		        $('#id').focus();
		        return;
			}
			$.get(
				"${pageContext.request.contextPath}/user.do"
				,{act:"idchk", inputID:$("#id").val()}
				,function(data,status){
					if(status=="success"){
						if(data > 0){//사용불가id
							$('#id_chk_res').text("이미 존재하는 아이디");
							$('#id_chk_res').addClass("text-danger");
							$('#id').focus;
							return;
						}else if(data == 0){
							$('#id_chk_res').text("사용가능한 아이디");
							$('#id_chk_res').addClass("text-primary");
							return;
						}else{
							alert("통신에러 다시시도해주세요");
							return;
						}
					}else{
						alert("통신에러 다시시도해주세요");
					}
				}
			);//get
		});//change
		
		$("#password").change(function() {
			$("pwd_chk_res").text("");
			$("pwd_chk_res").removeClass("text-primary");
			$("pwd_chk_res").removeClass("text-danger");
			if($("#password").val()==""){
				return;
			}
			var pwdReg = /^[A-Za-z0-9]{8,20}$/g;
			if(!pwdReg.test($("#password").val())){
				$("#pwd_chk_res").text("아이디의 형식이 잘못되었습니다. 형식:(영문 대소문자/숫자, 5~10자)");
				$("#pwd_chk_res").toggleClass("text-danger");
		        $('#password').focus();
		        return;
			}else{
				$("#pwd_chk_res").text("사용가능한 비밀번호");
				$('#pwd_chk_res').addClass("text-primary");
			}
		});//change
		$("#phone").change(function() {
			$("phone_chk_res").text("");
			$("phone_chk_res").removeClass("text-primary");
			$("phone_chk_res").removeClass("text-danger");
			if($("#phone").val()==""){
				return;
			}
			var pwdReg = /^[0-9]{11}$/g;
			if(!pwdReg.test($("#phone").val())){
				$("#phone_chk_res").text("형식이잘못되었습니다. 형식:01012341234");
				$("#phone_chk_res").toggleClass("text-danger");
		        $('#phone').focus();
		        return;
			}else{
				$("#phone_chk_res").text("OK");
				$('#phone_chk_res').addClass("text-primary");
			}
		});//change
		
	});//ready
	function signUp() {
		if (document.getElementById("id").value == "") {
			alert("아이디를 입력하세요");
			return;
		} else if (document.getElementById("password").value == "") {
			alert("비밀번호를 입력하세요");
			return;
		} else if (document.getElementById("name").value == "") {
			alert("이름을 입력하세요");
			return;
		} else if (document.getElementById("address").value == "") {
			alert("주소를 입력하세요");
			return;
		} else if (document.getElementById("phone").value == "") {
			alert("휴대폰번호를 입력하세요");
			return;
		} else {
			document.getElementById("signUpForm").action = "${root}/user.do?act=signUp";
			document.getElementById("signUpForm").submit();
			alert("회원가입 완료");
		}
	}
</script>
</head>

<body>
	<div id="wrapper">
		<%@ include file="/WEB-INF/page/common/menu.jsp"%>
		<div id="signUp">
			<h2>회원가입</h2>
			<div class="container" align="center">
				<div class="col-lg-6" align="center">
					<form id="signUpForm" method="post" action="">
						<input type="hidden" name="act" id="act" value="signUp">
						<div class="form-group" align="left">
							<label for="">아이디</label> <span id="id_chk_res"></span>
							<input type="text" class="form-control" id="id" name="id" placeholder="영문자로 시작하는 5 ~ 10글자 ">
						</div>
						<div class="form-group" align="left">
							<label for="">비밀번호</label> <span id="pwd_chk_res"></span>
							<input type="password" class="form-control" id="password" name="password" placeholder="숫자와 문자를 포함한 8~20글자" onkeydown="javascript:if(event.keyCode == 13) {login();}">
						</div>
						<div class="form-group" align="left">
							<label for="">이름</label>
							<input type="text" class="form-control" id="name" name="name" placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="">주소</label>
							<input type="text" class="form-control" id="address" name="address" placeholder="">
						</div>
						<div class="form-group" align="left">
							<label for="">휴대폰번호</label><span id="phone_chk_res"></span>
							<input type="text" class="form-control" id="phone" name="phone" placeholder="01012349876">
						</div>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-warning" onclick="javascript:signUp();">가입하기</button>
							<button type="reset" class="btn btn-warning">리셋</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/page/common/footer.jsp"%>
</body>
</html>