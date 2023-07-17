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
	나중에 실제 목록을 뿌려볼거예요.
	<a href="<%=request.getContextPath()%>/board/view.do">게시글 상세 페이지로 이동</a><br>
	<table border="1">
		<c:forEach items="${list }" var="board">
			<tr>
				<td>${board }</td>
			</tr>
		</c:forEach>
	</table>
	<table border="1">
		<% for(int i = 0; i < list.size(); i++)
		{
			String board = list.get(i);
		%>
		<tr>
			<td><%=board %></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>