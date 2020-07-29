<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- modal start -->
<div class="modal" id="loginModal">
	<div class="modal-dialog modal-md" style="vertical-align: middle;">
		<div class="modal-content">

			<!-- ModalHeader -->
			<div class="modal-header">
				<button type="button" class="close dataDismiss" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="form-group">
					<label for="mbr_id">ID</label> <input type="text" id="mbr_id"
						class="form-control" placeholder="Enter ID">
				</div>
				<div class="form-group">
					<label for="mbr_pwd">Password</label> <input type="password"
						id="mbr_pwd" class="form-control" placeholder="Enter Password">
				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="modalLogInBtn" class="btn btn-primary"
					data-dismiss="modal">LogIn</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- modal end -->

<script>
	$(document).ready(function() {
		$("#modalLogInBtn").click(function() {
			if ($.trim($("#mbr_id").val()) == '') {
				alert("ID를 입력해 주세요.");
				return;
			}
			if ($.trim($("#mbr_pwd").val()) == '') {
				alert("Password를 입력해 주세요.");
				return;
			}
			$.ajax({
				method : 'post',
				url : "${pageContext.request.contextPath}/user.do",
				async : true,
				dataType : "json",
				data : {
					act : 'login'
					,mbr_id : $("#mbr_id").val()
					,mbr_pwd : $("#mbr_pwd").val()
				},
				success : function(data){
					if(data == null){
						alert("잘못된 아이디/비밀번호");
					}else{
						alert("로그인성공");
						location.reload();
					}
				},
				error : function(data){
					alert("error");
				}
			}); 
		
/* 			$.ajaxSetup({ async:false });
			$.post(
				"${pageContext.request.contextPath}/user.do"
				, {
				act : 'login',
				mbr_id : $("#mbr_id").val(),
				mbr_pwd : $("#mbr_pwd").val()
			}, function(data, status) {
				debugger;
				if (status == "success") {
					if (${userinfo == null}) {
						alert("없는아이디/비밀번호입니다.");
					} else {
						//여기서 문제
						console.log(data);
						alert("로그인 성공");
					}
				} else {
					alert("시스템 관리자에게 문의 바랍니다.");
				}
			}//function
			, "json");//post
 */
		});//click
	});//ready
</script>