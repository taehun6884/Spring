<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	var checkIdResult = false;
	var checkPasswdResult = false;
	
	$(function() {
		$("#id").change(function() {
// 			alert($("#id").val());
// 			alert("$(this).val() : " + $(this).val());
			let id = $(this).val();

			// 아이디 첫 글자는 반드시 영문자, 두번째부터 16번째까지 영문자, 숫자, -_. 조합
			let regex = /^[A-Za-z][\w-.]{3,15}$/;
			if(!regex.exec(id)) { // 정규표현식과 일치하지 않을 경우
				$("#checkIdResult").html("4~16자리 영문,숫자,특수문자(-_.) 필수!");
				$("#checkIdResult").css("color", "red");
				checkIdResult = false; // 검증 결과 false 로 변경
			} else { // 정규표현식과 일치할 경우
				// AJAX 를 활용하여 데이터베이스로부터 아이디 검색
				// -----------------------------------------------
				$("#checkIdResult").html("사용 가능한 아이디!");
				$("#checkIdResult").css("color", "green");
				checkIdResult = true; // 검증 결과 true 로 변경
			}
		});
		
		// 패스워드 입력값 변경되면 정규표현식 검증 수행
		$("#passwd").change(function() {
			let passwd = $(this).val();

			// 영문자, 숫자, !@#$%_ 조합
			let regex = /^[\w!@#$%]{8,16}$/; // 전체 규칙만 검사(부분 검사 수행하지 않음)
			
			if(!regex.exec(passwd)) { // 정규표현식과 일치하지 않을 경우
				$("#checkPasswdResult").html("8~16자리 영문,숫자,특수문자(!@#$%_) 필수!");
				$("#checkPasswdResult").css("color", "red");
				checkPasswdResult = false; // 검증 결과 false 로 변경
			} else { // 정규표현식과 일치할 경우
				// 패스워드 복잡도(부분) 검사 수행
				let upperCaseRegex = /[A-Z]/;
				let lowerCaseRegex = /[a-z]/;
				let numRegex = /[0-9]/;
				let specRegex = /[!@#$%_]/;
				
				let count = 0; // 복잡도에 대한 점수 카운트 변수
				if(upperCaseRegex.exec(passwd)) { count++; }
				if(lowerCaseRegex.exec(passwd)) { count++; }
				if(numRegex.exec(passwd)) { count++; }
				if(specRegex.exec(passwd)) { count++; }
				
				// 복잡도 검사 결과(점수 판별)에 따라 메세지 출력
				switch(count) {
					case 4 :
						$("#checkPasswdResult").html("안전!");
						$("#checkPasswdResult").css("color", "blue");
						checkPasswdResult = true; // 검증 결과 true 로 변경
						break;	
					case 3 :
						$("#checkPasswdResult").html("보통!");
						$("#checkPasswdResult").css("color", "green");
						checkPasswdResult = true; // 검증 결과 true 로 변경
						break;	
					case 2 :
						$("#checkPasswdResult").html("위험!");
						$("#checkPasswdResult").css("color", "orange");
						checkPasswdResult = true; // 검증 결과 true 로 변경
						break;	
					default :
						$("#checkPasswdResult").html("사용 불가능한 패스워드!");
						$("#checkPasswdResult").css("color", "red");
						checkPasswdResult = false; // 검증 결과 false 로 변경
				}
			}
		});
		
		$('#domain').on("change",function(){
			$('#email2').val(this.val());
			
			if($(this).val() == ""){
				$("#email2").prop("readonly",false);
				$("#email2").css("background-color","white");
				$("#email2").focus();
			}else {
				$("#email2").prop("readonly",true);
				$("#email2").css("background-color","lightgray");
			}
		});
	
		$('form').submit(function() {
			if(!checkIdResult){
				alert("아이디를 확인해 주세요!");
				$('#id').focus();
				return false;
			}else if(!checkPasswdResult){
				alert("아이디를 확인해 주세요!");
				$('#passwd').focus();
				return false;
			}
		});
	
	});

</script>
</head>
<body>
	<c:if test="${sesscionScope.sId ne null }">
		<script type="text/javascript">
			alert("잘못된 접근입니다");
			loaction.href="./";
		</script>
	</c:if>
	<header>
		<!-- Login, Join 링크 표시 영역(inc/top.jsp 페이지 삽입) -->
<%-- 		<jsp:include page="/inc/top.jsp"></jsp:include> --%>
	</header>
	<h1>회원 가입</h1>
	<form action="MemberJoinPro.me" method="post" name="fr">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name" required="required" size="20"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="남">남&nbsp;&nbsp;
					<input type="radio" name="gender" value="여" checked="checked">여
				</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>
					<input type="text" name="email1" required="required" size="10">@
					<input type="text" name="email2" id="email2" required="required" size="10">
					<select name="selectDomain" onchange="emailCheck(this.value)">
						<option value="" id="domain">직접입력</option>	
						<option value="naver.com">naver.com</option>
						<option value="nate.com">nate.com</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" id="id"required="required" size="20" placeholder="4-16자리 영문자,숫자 조합">
					<span id="checkIdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
				</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td>
					<input type="password" name="passwd" id="passwd" required="required" size="20" placeholder="8-20자리 영문자,숫자,특수문자 조합">
					<span id="checkPasswdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원가입">
					<input type="button" value="취소" onclick="history.back()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>