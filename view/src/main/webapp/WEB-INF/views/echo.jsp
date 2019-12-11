<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹 소켓(Echo)</title>
</head>
<body>
	<input type="button" value="ECHO TEST" id="btn"/><br/>
</body>
<script>
	var websocket;
	document.getElementById("btn").addEventListener("click",function(e){
		// 웹 소켓 서버에 연결
		websocket = new WebSocket("ws://localhost:8080/view/echo-ws");
		// 웹 소켓이 연결되면 데이터를 전송하라는 것
		websocket.addEventListener("open", function(e){
			// 데이터를 전송
			websocket.send("안녕하세요");
		});
		// 메시지를 받으면 호출되는 콜백 함수
		websocket.addEventListener("message", function(e){
			// 서버가 보내 준 데이터 읽기
			alert(e.data);
			// 연결 해제
			websocket.close();
		});
	});
</script>
</html>