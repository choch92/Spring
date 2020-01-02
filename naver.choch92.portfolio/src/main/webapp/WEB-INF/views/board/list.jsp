<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<style>
p {margin: 8px 0px 8px 0px;}
a {text-decoration:none; display:inline-block;}
</style>
<div class="box">
	<div class="box-header with-border">
		<c:if test="${msg == null}">
			<h3 class="box-title">게시판 목록보기</h3>
		</c:if>
		<c:if test="${msg != null}">
			<h3 class="box-title">${msg}</h3>
		</c:if>
	</div>
	<div class="box-body">
		<table id ="p" class="table table-bordered table-hover">
			<tr bgcolor="papayawhip">
				<th width="9%">&nbsp;글번호</th>
				<th width="46%">&nbsp;제목</th>
				<th width="16%">&nbsp;작성자</th>
				<th width="16%">&nbsp;작성일</th>
				<th width="9%">&nbsp;조회수</th>
			</tr>
			<c:forEach var="vo" items="${list}">
				<tr>
					<td align="left">${vo.boardnum}&nbsp;</td>
					<td>&nbsp;${vo.boardtitle}</td>
					<td>&nbsp;${vo.nickname}</td>
					<td>&nbsp;${vo.dispdate}</td>
					<td align="center">
					<span class="badge bg-blue">${vo.boardreadcnt}</span>&nbsp;</td>
				</tr>
			</c:forEach>
			<c:if test="${d != null}">
				<tr id="c">
					<td colspan="5" align="center">
						<a href="#"><p id="the">더 보기</p></a>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<div class="box-footer">
		<div class="text-center">
			<button id='mainBtn' class="btn-primary">메인으로</button>
		</div>
	<script>
		document.getElementById('mainBtn').addEventListener("click", function(event) {
			location.href = "../";
		});
	</script>
	</div>
</div>
<%@include file="../include/footer.jsp"%>

<script>
	document.getElementById("the").addEventListener('click', function(e){
		$.ajax({
			url:"the",
			dataType:"json",
			success:function(map){
				// map의 데이터를 data에 저장
				var data = map.data;
				var p = document.getElementById("p");
				var c = document.getElementById("c");
				// p.removeChild(c);
				
				// jquery 에서 배열을 순회하기
				// 첫번째 매개변수는 배열이고 뒤의 매개변수는 배열의 데이터를 순회하면서 호출될 함수
				// 배열 각각의 데이터는 함수 내에서 this를 가지고 접근 가능
				var output = '';
				$.each(data, function(){
					output += "<tr>";
					output += "<td align='left'>" + this.boardnum
						+ "</td>";
					output += "<td>&nbsp;" + this.boardtitle + "</td>";
					output += "<td>&nbsp;" + this.nickname + "</td>";
					output += "<td>&nbsp;" + this.dispdate + "</td>";
					output += "<td align='center'><span class='badge bg-blue'>"
						+ this.boardreadcnt + "</span>&nbsp;</td>";
					output += "</tr>";
				});
				
				// c 앞에 출력
				$('#c').before(output);
				// 더보기 버튼 삭제
				if(map.d === "false"){
					$('#c').empty();
				}
			}
		});
	});
</script>

