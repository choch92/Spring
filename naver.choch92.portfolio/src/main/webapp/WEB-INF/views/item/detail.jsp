<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<section class="content">
	<div align="left" class="body">
		<h2>상품 상세 보기</h2>
		<table>
			<tr>
				<td><img style="border: 3px solid gold; border-radius: 7px;" 
						src="/img/${item.pictureurl}" /></td>
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
							<td colspan="2" align="center" width="240"><a href="/item/fruit">■목록으로 돌아가기</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</section>
<!-- footer.jsp 포함 -->
<%@ include file="../include/footer.jsp"%>