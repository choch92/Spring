<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="dummy1" content="에러페이지 에러페이지 에러페이지 에러페이지
에러페이지 에러페이지 에러페이지"/>
<meta name="dummy2" content="에러페이지 에러페이지 에러페이지 에러페이지
에러페이지 에러페이지 에러페이지"/>
<meta name="dummy3" content="에러페이지 에러페이지 에러페이지 에러페이지
에러페이지 에러페이지 에러페이지"/>
<meta name="dummy4" content="에러페이지 에러페이지 에러페이지 에러페이지
에러페이지 에러페이지 에러페이지"/>
<meta name="dummy1" content="에러페이지 에러페이지 에러페이지 에러페이지
에러페이지 에러페이지 에러페이지"/>
<title>예외 발생</title>
</head>
<body>
요청 처리 과정에서 예외가 발생하였습니다.<br>
빠른 시간 내에 문제를 해결하도록 하겠습니다.
<p>
에러 타입: <%= exception.getClass().getName() %> <br>
에러 메시지: <b><%= exception.getMessage() %></b>
</body>
</html>
