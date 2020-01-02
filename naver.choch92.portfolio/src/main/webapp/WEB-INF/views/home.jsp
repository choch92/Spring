<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp 파일을 추가 -->
<%@ include file="include/header.jsp"%>
<c:if test="${update != null }">
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#dialog-update").dialog({
				resizable : false,
				height : "auto",
				width : 400,
				modal : true,
				buttons : {
					"닫기" : function() {
						$(this).dialog("close");
					},
				}
			});
		});
	</script>
</c:if>

<c:if test="${delete != null }">
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#dialog-update").dialog({
				resizable : false,
				height : "auto",
				width : 400,
				modal : true,
				buttons : {
					"닫기" : function() {
						$(this).dialog("close");
					},
				}
			});
		});
	</script>
</c:if>

<div id="dialog-update" title="회원탈퇴" style="display: none">
	<p>
	<span class="ui-icon ui-icon-alert" style="float: left; margin: 12px 12px20px 0;">
	</span>회원탈퇴에 성공하셨습니다.</p>
</div>


<section class="content">
	<div class="box">

		<!-- 로그인이 안된 경우 -->
		<c:if test="${user == null}">
			<div class="box-header with-border">
				<a href="user/login"><h3 class="box-title">로그인</h3></a><br />
			</div>

			<div class="box-header with-border">
				<a href="user/join"><h3 class="box-title">회원가입</h3></a>
			</div>
		</c:if>
		<!-- 로그인이 된 경우 -->
		<c:if test="${user != null}">
			<div class="box-header with-border">
				<a href="user/logout"><h3 class="box-title">로그아웃</h3></a><br />
			</div>

			<div class="box-header with-border">
				<a href="user/update"><h3 class="box-title">회원정보수정</h3></a>
			</div>

			<div class="box-header with-border">
				<a href="user/delete"><h3 class="box-title">회원탈퇴</h3></a>
			</div>
		</c:if>

		<div id="dialog-update" title="회원정보 수정" style="display: none">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin: 12px 12px 20px 0;"></span>회원정보 수정에
				성공하셨습니다.이제 로그인하고 사용하시면 됩니다.
			</p>
		</div>
		
		<div id="dialog-update" title="회원탈퇴" style="display: none">
			<p>
				<span class="ui-icon ui-icon-alert" 
					style="float: left; margin: 12px 12px 20px 0;">
				</span>회원 탈퇴에 성공하셨습니다.
			</p>
		</div>

		<div class="box-header with-border">
			<p id="addr">
		</div>

		<div class="box-header with-border">
			<div id="map" style="width: 400px; height: 400px;"></div>
			<div id="clickLatlng"></div>
		</div>
		<br />

	</div>
</section>

<!-- footer.jsp 파일의 추가 -->
<%@ include file="include/footer.jsp"%>

<script>
	navigator.geolocation.getCurrentPosition(function(position) {
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		// jquery의 ajax
		$.ajax({
			url : 'address',
			data : {
				'longitude' : longitude,
				'latitude' : latitude
			},
			dataType : 'json',
			success : function(data) {
				document.getElementById('addr').innerHTML = "현재위치 : "
						+ data.address;
			}
		});
	});
</script>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=abfe30c50bf8646b3518958413f646c1">
	
</script>
<script>
	navigator.geolocation.getCurrentPosition(function(position) {
		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(position.coords.latitude,
					position.coords.longitude),
			level : 3
		};
		var map = new kakao.maps.Map(container, options);

		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

		// 지도를 클릭한 위치에 표출할 마커입니다
		var marker = new kakao.maps.Marker({
			// 지도 중심좌표에 마커를 생성합니다 
			position : map.getCenter()
		});
		// 지도에 마커를 표시합니다
		marker.setMap(map);

		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

			// 클릭한 위도, 경도 정보를 가져옵니다 
			var latlng = mouseEvent.latLng;

			// 마커 위치를 클릭한 위치로 옮깁니다
			marker.setPosition(latlng);

			var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
			message += '경도는 ' + latlng.getLng() + ' 입니다';

			var resultDiv = document.getElementById('clickLatlng');
			resultDiv.innerHTML = message;

		});

	}, function(error) {

	});
</script>
