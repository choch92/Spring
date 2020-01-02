<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet" href="../css/fruit.css"/>

<section class="content">
	<!-- JSTL의 core 기능을 사용하기 위한 태그 라이브러리를 설정 -->
	<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div align="left" class="body">
		<h2>상품 목록</h2>
		<table border="3" bordercolor="silver" cellpadding="7" height="300" align="left">
				<tr class="header" >
					<th width="100">&nbsp;&nbsp;상품 ID</th>
					<th width="250">&nbsp;&nbsp;상품 이름</th>
					<th width="100">&nbsp;&nbsp;가격</th>
				</tr>
				<c:forEach var="item" items="${list}">
					<tr class="record">				
						<td align="center">${item.itemid}</td>
						<td>&nbsp;&nbsp;<a href="detail/${item.itemid}">${item.itemname}</a></td>
						<td align="right">${item.price}원&nbsp;&nbsp;</td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<div align="left"><input type="button" value="메인으로" class="btn btn-primary"	id="mainbtn"/></div>
</section>
<script>
	//메인으로 버튼을 눌렀을 때 메인으로 이동하도록 설정
	document.getElementById("mainbtn").addEventListener("click", function(e){
		// 시작 페이지로 이동
		location.href="/";
	});
</script>
<!-- footer.jsp 포함 -->
<%@ include file="../include/footer.jsp"%>