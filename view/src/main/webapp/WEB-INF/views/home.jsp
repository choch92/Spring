<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>다양한 뷰 출력</title>
</head>
<body>
	<div>
		<h2>다양한 뷰 출력해보기</h2>
		<table border="5" bordercolor="silver" cellpadding="3px">
		<tr bgcolor="skyblue">
				<td width="200">Spring 다양한 출력</td>
				<td width="200">Spring Custom Tag</td>
		</tr>
		<tr>
			<td><a href="fileview" target="test">파일 목록 보기</a></td>
			<td><a href="login" target="test">로그인[Spring-Properties]</a></td>
		</tr>
		<tr>
			<td><a href="./excel.xls" target="test" >엑셀 파일 다운로드</a></td>
			<td><a href="join" target="test">회원가입[Spring-Form 유효성]</a></td>
		</tr>
		<tr>
			<td><a href="./oop.pdf" target="test" >PDF 파일 다운로드</a></td>
			<td><a href="birth" target="test">Birth[Spring-Form 유효성]</a></td>
		</tr>
		<tr>
			<td><a href="./view.json" target="test" >view를 이용한 JSON 다운로드</a></td>
			<td><a href="fileupload" target="test">파일 업로드 처리</a></td>
		</tr>
		<tr>
			<td><a href="./rest.csv" target="test" >CSV 다운로드(RestController)</a></td>
			<td></td>
		</tr>
		<tr>
			<td><a href="./rest.json" target="test" >JSON 다운로드(RestController)</a></td>
			<td></td>
		</tr>
		<tr>
			<td><a href="./book.xml">XML 다운로드</a></td>
		</tr>
		</table>
	</div>
	
	<iframe width="100%" height="600" name="test" id="test" frameborder="1" 
	scrolling="yes" align="left"></iframe>
</body>
</html>
