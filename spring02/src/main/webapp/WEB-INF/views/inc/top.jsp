<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="member_area" align="right">
	<c:choose>
		<c:when test='${sessionScope.sId eq "admin" }'>
		<a href="MemberList.me">회원목록</a> | ${sId } | <a href="logout.me">로그아웃</a>
		</c:when>
		<c:when test="${not empty sessionScope.sId }">
			<a href="./">Home</a> |	${sId } | <a href="logout.me">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="login.me">Login</a> | <a href="MemberInsertForm.me">Join</a>
		</c:otherwise>
	</c:choose>
</div>    
<div id="menu">
	<a href="<%=request.getContextPath()%>">홈</a>
	<a href="<%=request.getContextPath()%>/main">메인페이지</a>
	<a href="<%=request.getContextPath()%>/push">데이터 전달</a>
	<a href="<%=request.getContextPath()%>/redirect">리다렉트</a>
	<a href="<%=request.getContextPath()%>/mav">ModelAndView</a>
	<a href="<%=request.getContextPath()%>/login.me">로그인</a>
	<a href="<%=request.getContextPath()%>/write.bo">글쓰기</a>
</div>