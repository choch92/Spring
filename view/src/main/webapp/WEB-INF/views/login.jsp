<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<!-- Spring의 message 태그를 사용하기 위한 태그 라이브러리 설정 -->
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
	<form method="post">
		<spring:message code="email"/>&nbsp;&nbsp;&nbsp;<input type="text" required="required" 
		name="email" id="email" size="30"/><br/>
		<spring:message code="password"/><input type="password" required="required" 
		name="password" id="password" size="30"/><br/>
		<input type="submit" value="로그인"/><br/>
	</form>
</body>
</html>