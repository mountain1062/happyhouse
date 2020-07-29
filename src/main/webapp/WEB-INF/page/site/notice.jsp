<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<c:if test="${ !empty result }">
<script type="text/javascript">
  $(function (){
	  alert("${result}");
	  location.href="${root}/main.do?act=noticelist";
  })
</script>
</c:if>
<script>
	function modify(){
		$("#act").val('mvnoticemodify');
		$("#form1").submit();
	}
	function deletenotice(){
		$("#act").val('noticedelete');
		$("#form1").submit();
	}
</script>
</head>
<body>

<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>
<br>
	<h2>공지사항</h2>
	<div class="container" align="center">
		<div class="col-lg-6" align="center">
			<div class="form-group" align="left">
				<label for="subject">제목:</label>
				<input type="text" class="form-control" id="subject" name="subject" disabled="disabled" value="${ notice.subject }">
			</div>
			<div class="form-group" align="left">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="15" id="content" name="content" disabled="disabled">${ notice.content }</textarea>
			</div>	
			<c:if test="${ userinfo != null && userinfo.admin }">
			<form name="form1" id="form1" action="main.do">
			<input type="hidden" name="articleno" value="${ notice.articleno }"/>		
			<input type="hidden" name="act" id="act" value="mvnoticemodify"/>		
			<button type="button" class="btn btn-primary" onclick="javascript:modify();">글수정</button>
			<button type="button" class="btn btn-danger" onclick="javascript:deletenotice();">글삭제</button>
			</form>
			</c:if>
			<a href="main.do?act=noticelist"><button type="button" class="btn btn-warning">목록으로</button></a>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>