<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<!-- 외부 CSS 가져오기 -->
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
	#writeForm {
		width: 500px;
		height: 450px;
		border: 1px solid red;
		margin: auto;
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
		text-align: center;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btnRequestAjax").on("click", function() {
			// 스프링 컨트롤러에 AJAX 로 JSON 데이터 전송하여 요청
			// => 요청 데이터 형식 변경을 위해 contentType 속성에 "application/json" 타입 지정
			//    (생략 시 기본 타입 : "application/x-www-form-urlencoded" 타입)
// 			$.ajax({
// 				type : "POST",
// 				url : "BoardWriteProAjax.bo",
// 				dataType : "text", // 컨트롤러에서 응답받을 데이터 타입
// 				contentType : "application/json", // 컨트롤러로 전달할 요청 데이터 타입
// 				// 전송할 JSON 데이터를 하나의 문자열로 직접 결합하여 전송
// 				data : "{'board_name':'홍길동','board_pass':'1234'}",
// 				success : function(data) {
// 					alert(data);
// 				}
// 			});
			
			var requestData = {
				"board_name" : $("#board_name").val(),
				"board_pass" : $("#board_pass").val()
			};
			
			$.ajax({
				type : "POST",
				url : "BoardWriteProAjax.bo",
				dataType : "text", // 컨트롤러에서 응답받을 데이터 타입
				contentType : "application/json", // 컨트롤러로 전달할 요청 데이터 타입
				// 전송할 JSON 데이터를 하나의 문자열로 직접 결합하여 전송
				data : JSON.stringify(requestData),
				success : function(data) {
// 					alert(data);
					if(data == "성공") {
						alert("성공!");
					} else {
						alert("실패!");
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h1>게시판 글 등록</h1>
		<!-- 파일 업로드 기능 사용 위해 enctype 속성 설정 => cos.jar 라이브러리 필요 -->
		<form action="BoardWritePro.bo" name="boardForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right"><input type="text" id="board_name" name="board_name" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_pass">비밀번호</label></td>
					<td class="td_right">
						<input type="password" id="board_pass" name="board_pass" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제목</label></td>
					<td class="td_right"><input type="text" name="board_subject" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내용</label></td>
					<td class="td_right">
						<textarea id="board_content" name="board_content" cols="40" rows="15" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_file">파일 첨부</label></td>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td class="td_right">
<!-- 						<input type="file" name="file" /> -->
						<!-- 복수개의 파일을 각각의 입력폼으로 처리할 경우(name 속성 동일하게) -->		
						<input type="file" name="files" />
						<br><input type="file" name="files" />
						<br><input type="file" name="files" />
						
						<!-- 복수개의 파일을 하나의 입력폼으로 처리할 경우(mutliple 속성 필요) -->		
<!-- 						<input type="file" name="files" multiple="multiple" /> -->
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
				| <input type="button" value="AJAX 요청" id="btnRequestAjax">
			</section>
		</form>
	</section>
</body>
</html>








