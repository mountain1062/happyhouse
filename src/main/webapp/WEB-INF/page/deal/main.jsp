<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<script src="js/map.js"></script>
<script>
$(function (){
	$.get("${root}/SelectBoxController"
		, {command : 'sido'}
		,function (data, status){
			$.each(data, function (idx, vo){
				let tmpStr = "<option value=" + vo.sido_code+">"+vo.sido_name+"</option>";
				$("#sido").append(tmpStr);
			});
		}
		, "json"
	);
	$("#sido").change(function(){
		$("#gugun").empty().append("<option value='0'>구/군</option>");
		$.get("${root}/SelectBoxController"
				, {command : 'gugun', sido : $("#sido").val()}
				,function (data, status){
					$.each(data, function (idx, vo){
						let tmpStr = "<option value=" + vo.gugun_code+">"+vo.gugun_name+"</option>";
						$("#gugun").append(tmpStr);
					});
				}
				, "json"
			);
	});
	$("#gugun").change(function(){
		$("#dong").empty().append("<option value='0'>읍/면/동</option>");
		$.get("${root}/SelectBoxController"
				, {command : 'dong', gugun : $("#gugun").val()}
				,function (data, status){
					$.each(data, function (idx, vo){
						let tmpStr = "<option value=" + vo.code+">"+vo.dong+"</option>";
						$("#dong").append(tmpStr);
					});
				}
				, "json"
			);
	});
	$("#dong").change(function (){
		/* $.get("${pageContext.request.contextPath}/SelectBoxController"
				, {command : 'apt', dong : $("#dong").val() }
				, function (data, status){
					$.each(data, function(idx, vo){
						addMarker(vo.lat, vo.lng, vo.name);
					});
				}
				,"json"
			); */
		$("#dealsearchform").submit();
	});
});
</script>
</head>
<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp"%>
<!-- location selection start -->
<section>
	<form name="dealsearchform" id="dealsearchform" action="deal.do">
		<input type="hidden" name="act" value="search"/>
		<input type="hidden" name="dealtype" value="dong"/>
		<div>
			<input type="checkbox" name="chksearchtype" id="chkaptdeal" value="0" ${aptdeal}/>
			<label for="chkaptdeal">아파트 매매</label>
			<input type="checkbox" name="chksearchtype" id="chkaptrent" value="1" ${aptrent}/> 
			<label for="chkaptrent">아파트 전월세</label>
			<input type="checkbox" name="chksearchtype" id="chkhousedeal" value="2" ${housedeal}/> 
			<label for="chkhousedeal">다세대,주택 매매</label>
			<input type="checkbox" name="chksearchtype" id="chkhouserent" value="3" ${houserent}/> 
			<label for="chkhouserent">다세대,주택 전월세</label>
		</div>
		<div class="form-group">
			<select id="sido" name="sido" class="form-control inline-fit">
				<option value="0">시/도</option>
			</select> 
			<select id="gugun" name="gugun" class="form-control inline-fit">
				<option value="0">구/군</option>
			</select> 
			<select id="dong" name="dong" class="form-control inline-fit">
				<option value="0">읍/면/동</option>
			</select>
		</div>
	</form>
</section>
<!-- location selection end -->

<!-- map start -->
<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC3Jh6Rt72qHXe5GomCfP_4LAuHjs_sr0U&callback=initMap"></script>
<!-- map end -->

</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>