<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	List<String> list = (List<String>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 목록</h2>
	게시글 목록 화면입니다.
	<br>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list }" var="board">
			<tr>
				<td>${board.bidx }</td>
				<td><a href="view.do?bidx=${board.bidx }">${board.title }</a></td>
				<td>${board.wdate }</td>
				<td>${board.hit }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>