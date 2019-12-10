<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>생년월일 입력</title>
<style>
span {
	color: red;
}
</style>
</head>
<body>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<form:form modelAttribute="command">
		년도<form:input path="year" />
		<form:errors path="year" />
		<br />
		월<form:input path="month" />
		<form:errors path="month" />
		<br />
		일<form:input path="day" />
		<form:errors path="day" />
		<br />
		<input type="submit" value="전송" />
		
	</form:form>
</body>
</html>