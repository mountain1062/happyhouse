/**
 * Google map 관련
 */

var loc = {lat: 37.5665734, lng: 126.978179};
var map;
function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center: loc, zoom: 12
	});
	var marker = new google.maps.Marker({position: loc, map: map});
}
function addMarker(tmpLat, tmpLng, aptName) {
	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
		label: aptName,
		title: aptName
	});
	marker.addListener('click', function() {
		map.setZoom(17);
		map.setCenter(marker.getPosition());
		callHouseDealInfo();
	});
	marker.setMap(map);
}
function callHouseDealInfo() {
	alert("you can call HouseDealInfo");
}