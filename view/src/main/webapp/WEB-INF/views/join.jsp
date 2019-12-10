<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
<style>
	span{
		color:red;
	}
</style>
</head>
<body>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
	<form:form modelAttribute="command">
		아이디<form:input path="id"/>
		<form:errors path="id"/><br/>
		비밀번호<form:input path="pw"/>
		<form:errors path="pw"/><br/>
		이름<form:input path="name"/>
		<form:errors path="name"/>
		<input type="submit" value="회원가입"/>		
	</form:form>
</body>
</html>