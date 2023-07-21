<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 상세</h2>
	<table>
		<tr>
			<th align="right">제목:</th>
			<td>${vo.title} </td>
		</tr>
		<tr>
			<th align="right">작성자id:</th>
			<td>${vo.id}</td>
		</tr>
		<tr>
			<th align="right">작성일:</th>
			<td>${vo.wdate}</td>
		</tr>
		<tr>
			<th align="right">조회수:</th>
			<td>${vo.hit}</td>
		</tr>
		<tr height="100px">
			<th align="right">내용:</th>
			<td>${vo.body}</td>
		</tr>
	</table>
	
	<c:if test="${not empty login and login.id eq vo.id}">
	<button onclick="location.href='modify.do?bidx=${vo.bidx }'">수정하기</button>
	<button onclick="delFn()">삭제하기</button>
	</c:if>
	<form name="frm" action="delete.do" method="post">
		<input type="hidden" name="bidx" value="${vo.bidx }">
	</form>
	<script>
		function delFn()
		{
			document.frm.submit();
		}
	</script>
</body>
</html>