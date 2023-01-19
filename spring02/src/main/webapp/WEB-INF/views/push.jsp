<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
	<!-- inc/top.jsp 페이지 삽입 -->
	<jsp:include page="inc/top.jsp"/>	
</header>
	<h1>push.jsp</h1>
	<h3>msg 속성 값 :${msg }</h3>
	<h3>msg 속성 값 :<%=request.getAttribute("msg") %></h3>
	<hr>
	<h3>test 속성값 제목 :  ${test.subject }</h3>
	<h3>test 속성값 내용 :  ${test.content }</h3>
	
</body>
</html>