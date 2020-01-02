<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<div class="login-box well">
				<form accept-charset="UTF-8" role="form" method="post" action="delete">
					<legend>비밀번호 확인 페이지</legend>
					<div style='color: red'>${msg}</div>
					<div class="form-group">
						<label for="password">비밀번호 </label> 
						<input type="password" name="pw" id="pw" 
						placeholder="비밀번호를 입력하세요" class="formcontrol" />
					</div>
					<div class="form-group">
						<input type="submit"
							class="btn btn-primary btn-login-submit btn-block m-t-md"
							value="회원탈퇴" />
					</div>
					<div class="form-group">
						<a href="../" class="btn btn-success btn-block m-tmd">메인으로</a>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>

<script>

	var pw = document.getElementById("pw");
	
	var pwcheck = false;
	
	pw.addEventListener("focusout", function(e){
		var pwvalue = pw.value.trim();
		
		// ajax 요청 객체 생성
		var request = new XMLHttpRequest();
		// 요청 주소 생성
		requset.open("GET", "pwcheck?password=" + pwvalue);
		// 요청
		request.sent("");
		//ajax 콜백 함수 등록(비동기적이기 때문에)
		request.onreadystatechange=function(){
			//정상응답이 오면
			if(request.readyState==4){
				if(request.status>=200 && request.status<300){
					
					var obj=request.responseText;
					//json 문자열 파싱
					var json=JSON.parse(obj);
					if(json.result==true){
						pwcheck=true;
					}else{
						pwcheck=false;
					}
				}
			}
		}
	});

</script>

<%@ include file="../include/footer.jsp"%>