<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
	String serverTime = (String)request.getAttribute("serverTime");
%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! Spring <%=serverTime %>
</h1>
<c:if test="${empty login }">
<a href="<%=request.getContextPath()%>/user/login.do">로그인</a>

<a href="<%=request.getContextPath()%>/user/join.do">회원가입</a>
<!-- 아이디 패스워드 이름 나이 성별 주소 핸드폰 
	아이디 패스워드 이름은 필수 입력
	grade는 기본 C
-->
</c:if>
<c:if test="${not empty login }">
${login.name }님 환영합니다.
<a href="<%=request.getContextPath()%>/user/logout.do">로그아웃</a>
</c:if>
<P>  The time on the server is ${serverTime}. </P>
<br>

<p>
	<a href="<%=request.getContextPath()%>/sample.do">sample.do 로 이동</a>
</p>
<p>
	<a href="<%=request.getContextPath()%>/board/list.do">board/list.do로 이동</a>
</p>
	<a href="<%=request.getContextPath()%>/user/list.do">회원목록으로 이동</a>
	
<p>
	<a href="<%=request.getContextPath()%>/ajax/sample.do ">ajax 화면</a>
</p>

<p>
	<a href="<%=request.getContextPath()%>/fileupload.do">파일 업로드하러 가기</a>
</p>
<c:if test="${not empty param.fileNM}">
<img src="<%=request.getContextPath()%>/resources/upload/${param.fileNM}">
</c:if>
</body>
</html>
