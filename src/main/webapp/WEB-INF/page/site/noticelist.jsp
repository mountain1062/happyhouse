<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<script>
	function show(no){
		location.href="${root}/main.do?act=notice&no=" + no;
	}
	
	function write(){
		location.href="${root}/main.do?act=mvnoticewrite";
	}
</script>
</head>
<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>
<br>
	<h2>공지사항</h2>
	<br>
	<c:if test="${ userinfo != null && userinfo.admin }">
	<div>
		<a href="main.do?act=mvnoticewrite"><button type="button" class="btn btn-warning">글 작성</button></a>
	</div>
	<br>
	</c:if>
	<table class="list-table table table-bordered table-responsive table-sm table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${ notices != null && notices.size() > 0}">
			<c:forEach var="notice" items="${ notices }">
			<tr onclick="show(${notice.articleno})">
				<td>${ notice.articleno }</td>
				<td>${ notice.subject }</td>
				<td>${ notice.username }</td>
				<td>${ notice.regtime }</td>
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${notices == null || notices.size() == 0 }">
			<tr>
				<td colspan="4">게시된 글이 없습니다.</td>
			</tr>
		</c:if>
		</tbody>
	</table>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>