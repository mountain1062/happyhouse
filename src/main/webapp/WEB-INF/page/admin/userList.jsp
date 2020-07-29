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
	<h2>회원 리스트</h2>
	  <table class="table table-active">
	  	<thead>
	  	<tr class="label">
		  	<th>아이디</th>
		  	<th>이름</th>
		  	<th>주소</th>
		  	<th>휴대폰</th>
	  	</tr>
	  	</thead>
	  	
	    <tbody>
	    	<c:forEach var="member" items="${list}">
		      <tr class="table-info">
		        <td align="left">${member.id }</td>
		        <td align="left">${member.name }</td>
		        <td align="left">${member.address }</td>
		        <td align="left">${member.phone }</td>
		      </tr>
	    	</c:forEach>
	    </tbody>
	  </table>
	  
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>