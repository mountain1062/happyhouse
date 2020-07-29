<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<c:if test="${ !userinfo.admin }">
	<c:redirect url="main.do"/>
</c:if>
<c:if test="${ !empty result }">
<script type="text/javascript">
  $(function (){
	  alert("${result}");
	  location.href="${root}/main.do?act=noticelist";
  })
</script>
</c:if>
  <script type="text/javascript">
  function writeArticle() {
	if(document.getElementById("subject").value == "") {
		alert("제목을 입력하세요.");
		$("#subject").focus();
		return;
	} else if(document.getElementById("content").value == "") {
		alert("내용을 입력하세요.");
		$("#content").focus();
		return;
	} else {
	  	document.getElementById("writeform").action = "${root}/main.do";
	  	document.getElementById("writeform").submit();
	}
  }
  </script>
</head>
<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>
<br>
	<h2>공지사항</h2>
	<div class="col-lg-6" align="center">
		<form id="writeform" method="post" action="">
			<c:if test="${ notice == null }">
			<input type="hidden" name="act" id="act" value="noticewrite">
			<div class="form-group" align="left">
				<label for="subject">제목:</label>
				<input type="text" class="form-control" id="subject" name="subject">
			</div>
			<div class="form-group" align="left">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="15" id="content" name="content"></textarea>
			</div>
			</c:if>
			<c:if test="${ notice != null }">
			<input type="hidden" name="act" id="act" value="noticemodify">
			<input type="hidden" name="articleno" id="articleno" value="${ notice.articleno }">
			<div class="form-group" align="left">
				<label for="subject">제목:</label>
				<input type="text" class="form-control" id="subject" name="subject" value="${ notice.subject }">
			</div>
			<div class="form-group" align="left">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="15" id="content" name="content">${ notice.content }</textarea>
			</div>
			</c:if>
			<button type="button" class="btn btn-primary" onclick="javascript:writeArticle();">저장</button>
			<a href="main.do?act=noticelist"><button type="button" class="btn btn-warning">취소</button></a>
		</form>
	</div>
</div>
	
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>