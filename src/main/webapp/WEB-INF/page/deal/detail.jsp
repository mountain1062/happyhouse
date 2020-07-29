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
<article>
	<div>
	<c:if test="${deal.img == null}">
		<img src="${root}/img/다세대주택.jpg"/>
	</c:if>	
	<c:if test="${deal.img != null}">
		<img src="${root}/img/${deal.img}"/>
	</c:if>	
	</div>
	<div id="houseinfo">
		<div class="col-sm-12">
			<div class="col-sm-3"><label>주택명</label></div>
			<div class="col-sm-3">${deal.aptName }</div>
		</div>
		<div class="col-sm-12">
			<div class="col-sm-3"><label>거래금액</label></div>
			<div class="col-sm-3">${deal.dealAmount }</div>
		</div>
		<c:if test="${ !empty deal.rentMoney }">
		<div class="col-sm-12">
			<div class="col-sm-3"><label>월세금액</label></div>
			<div class="col-sm-3">${deal.rentMoney }</div>
		</div>
		</c:if>
		<div class="col-sm-12">
			<div class="col-sm-3"><label>건축연도</label></div>
			<div class="col-sm-3">${deal.buildYear }</div>
		</div>
		<div class="col-sm-12">
			<div class="col-sm-3"><label>전용면적</label></div>
			<div class="col-sm-3">${deal.area }</div>
		</div>
		<div class="col-sm-12">
			<div class="col-sm-3"><label>거래일</label></div>
			<div class="col-sm-3">${deal.dealYear }/${deal.dealMonth }/${deal.dealDay }</div>
		</div>
		<div class="col-sm-12">
			<div class="col-sm-3"><label>법정동</label></div>
			<div class="col-sm-3">${deal.dong }</div>
		</div>
		<div class="col-sm-12">
			<div class="col-sm-3"><label>지번</label></div>
			<div class="col-sm-3">${deal.jibun }</div>
		</div>
	</div>

</article>


</div>
	
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>