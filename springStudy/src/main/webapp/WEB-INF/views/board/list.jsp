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
	<form action="list.do" method="get">
		<select name="searchType">
			<option value="title" <c:if test="${param.searchType eq 'title'}">selected</c:if>>제목</option>
			<option value="id" <c:if test="${param.searchType eq 'id'}">selected</c:if>>id</option>
		</select>
		<input type="text" name="searchValue" size="30" value="${param.searchValue}">
		<button>검색</button>
	</form>
	<br>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>글쓴이</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list }" var="board">
			<tr>
				<td>${board.bidx }</td>
				<td>${board.id }</td>
				<td><a href="view.do?bidx=${board.bidx }">${board.title }</a></td>
				<td>${board.wdate }</td>
				<td>${board.hit }</td>
			</tr>
		</c:forEach>
		<c:if test="${not empty login }">
		<a href="<%=request.getContextPath()%>/board/write.do">글쓰기로 이동</a><br>
		</c:if>
		<a href="view.do">게시글 상세페이지로 이동</a>
	</table>
</body>
</html>