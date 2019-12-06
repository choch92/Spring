<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세보기</title>
</head>
<body>
	<div align="center" class="body">
		<h2>상품 상세 보기</h2>
		<table>
			<tr>
				<td><img style="border: 3px solid gold; border-radius: 7px;" 
						src="../img/${item.pictureurl}" /></td>
				<td align="center">
					<table>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160">${item.itemname}</td>
						</tr>
						<tr height="50">
							<td width="80">가격</td>
							<td width="160">${item.price}원</td>
						</tr>
						<tr height="50">
							<td width="80">상품내용</td>
							<td width="160">${item.description}</td>
						</tr>
						<tr>
							<td colspan="2" align="center" width="240"><a href="../">■목록으로 돌아가기</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
