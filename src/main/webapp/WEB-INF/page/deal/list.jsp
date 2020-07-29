<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/header.jsp" %><script src="js/map.js"></script>
<%-- <c:set var="aptdeal" value=" checked"/>
<c:set var="aptrent" value=" checked"/>
<c:set var="housedeal" value=" checked"/>
<c:set var="houserent" value=" checked"/>
<c:if test="${ searchtype != null }">
	<c:if test="${ !searchtype[0] }">
	<c:set var="aptdeal" value=""/>
	</c:if>
	<c:if test="${ !searchtype[1] }">
	<c:set var="aptrent" value=""/>
	</c:if>
	<c:if test="${ !searchtype[2] }">
	<c:set var="housedeal" value=""/>
	</c:if>
	<c:if test="${ !searchtype[3] }">
	<c:set var="houserent" value=""/>
	</c:if>
</c:if> --%>
<c:set var="sido" value="${ sido }"/>
<c:set var="gugun" value="${ gugun }"/>
<c:set var="dong" value="${ dong }"/>
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
	
	$("#sido").change(bindGugun);
	$("#gugun").change(bindDong);
	$("#dong").change(markApt);
});
	$(function (){
		if('${dealtype}' != ''){
			$("#dealtype").val('${dealtype}');
		}
	})
	
	function search(){
		if($("#dealtype").val() !='all' && $("#dealkeyword").val() == ''){
			alert("검색어를 입력해주세요.");
			$("#dealkeyword").focus();
			return false;
		}
	}

	function view(no){
		location.href = "${root}/deal.do?act=detail&no="+no;
	}
	
	function bindGugun(){
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
	}
	
	function bindDong(){
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
	}
	
	function markApt(){
		$.ajax({  
            type: 'POST',  
            url: "${root}/deal.do",  
            dataType: "json",
            data: {
					act : 'searchjson'
					/* , chksearchtype : $("input[name=chksearchtype]:checked").val() */
					, dealtype : 'dong'
					, sido : $("#sido").val()
					, gugun : $("#gugun").val()
					, dong : $("#dong").val() } ,
            contentType: "application/x-www-form-urlencoded; application/json; charset=UTF-8",  
            timeout: 10000,  
            datatype: 'json',  
            success: function (data) {  
            	/* $("#dealresult") */
				$.each(data, function(idx, vo){
					addMarker(vo.lat, vo.lng, vo.AptName);
				});
            },
            error:function (){
            	alert("에러");
            }
		});  
	}
</script>
</head>
<body>
<div id="wrapper">
<%@ include file="/WEB-INF/page/common/menu.jsp" %>

<!-- location selection start -->
<section id="dealsearch">
<%-- 	<div>
		<input type="checkbox" name="chksearchtype" id="chkaptdeal" value="0" ${aptdeal}/>
		<label for="chkaptdeal">아파트 매매</label>
		<input type="checkbox" name="chksearchtype" id="chkaptrent" value="1" ${aptrent}/> 
		<label for="chkaptrent">아파트 전월세</label>
		<input type="checkbox" name="chksearchtype" id="chkhousedeal" value="2" ${housedeal}/> 
		<label for="chkhousedeal">다세대,주택 매매</label>
		<input type="checkbox" name="chksearchtype" id="chkhouserent" value="3" ${houserent}/> 
		<label for="chkhouserent">다세대,주택 전월세</label>
	</div> --%>
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
</section>
<!-- location selection end -->
<!-- <script>
$(function (){
	if("${sido}" != ''){
		$("#sido").val("${sido}");
		bindGugun();
	}
	if("${gugun}" != ''){
		$("#gugun").val("${gugun}");
		bindDong();
	}
	if("${dong}" != ''){
		$("#dong").val("${dong}");
		markApt();
	}
})

</script> -->
<h4>거래 내역</h4>
<section class="row">
	<!-- 거래 내역 start -->
	<section class="col-lg-5">
		<c:if test="${ deals != null }">
		<table class="list-table table table-bordered table-responsive table-sm table-hover">
			<thead>
				<tr>
					<td>번호</td>
					<td>동</td>
					<td>아파트이름</td>
					<td>거래금액</td>
					<td>거래종류</td>
				</tr>
			</thead>
			<tbody id="dealresult">
				<c:forEach var="deal" items="${ deals }">
					<tr onclick="view(${ deal.no })">
						<td>${ deal.no }</td>
						<td>${ deal.dong }</td>
						<td>${ deal.aptName }</td>
						<td>${ deal.dealAmount }</td>
						<td>${ deal.type }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
	</section>
	<!-- 거래 내역 end -->
	
	
	<!-- map start -->
	<section class="col-lg-6">
		<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
		<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
		<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC3Jh6Rt72qHXe5GomCfP_4LAuHjs_sr0U&callback=initMap"></script>
	</section>
	<!-- map end -->
</section>


</div>

<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
</html>