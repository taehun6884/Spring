<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<!-- 외부 CSS 가져오기 -->
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
	#modifyForm {
		width: 500px;
		height: 500px;
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
	function deleteFile(fileName, index) {
		// 패스워드 입력란이 비어있을 경우 오류메세지 출력 및 작업 중단
		let board_pass = $("#board_pass").val();
		if(board_pass == "") {
			alert("패스워드 입력 필수!");
			$("#board_pass").focus();
			return;
		}
		
// 		alert(${board.board_num} + ", " + fileName + ", " + index);
		// 파일 삭제를 AJAX 로 처리하기 위해 BoardDeleteFile.bo 서블릿 요청
		// => 파라미터 : 글번호, 파일명
		$.ajax({
			type: "POST",
			url: "BoardDeleteFile.bo",
			data: {
				"board_num" : ${board.board_num},
				"board_pass" : board_pass,
				"fileName" : fileName
			},
			success: function(data) {
				if(data == "true") {
					// 삭제 성공 시 파일명 표시 위치의 기존 항목을 제거하고
					// 파일 업로드를 위한 "파일 선택" 버튼 항목 표시
					$("#fileArea" + index).html('<input type="file" name="files">');
				} else if(data == "false") {
					alert("일시적인 오류로 파일 삭제에 실패했습니다!");
				} else if(data == "incorrectPasswd") {
					alert("패스워드 틀림!");
					$("#board_pass").select();
				}
			}
		});
	}
</script>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 글 수정 -->
	<section id="modifyForm">
		<h1>게시판 글 수정</h1>
		<form action="BoardModifyPro.bo" name="boardForm" method="post" enctype="multipart/form-data">
			<!-- 입력받지 않은 글번호, 페이지번호 hidden 속성으로 전달 -->
			<input type="hidden" name="board_num" value="${param.board_num }" >
			<input type="hidden" name="pageNum" value="${param.pageNum }" >
			<table>
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right"><input type="text" name="board_name" value="${board.board_name }" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_pass">비밀번호</label></td>
					<td class="td_right"><input type="password" id="board_pass" name="board_pass" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제목</label></td>
					<td class="td_right"><input type="text" name="board_subject" value="${board.board_subject }" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내용</label></td>
					<td class="td_right">
						<textarea id="board_content" name="board_content" cols="40" rows="15" required="required">${board.board_content }</textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_file">파일</label></td>
					<td class="td_right">
						<c:set var="arrRealFile" value="${fn:split(board.board_real_file, '/') }"/>
						<%-- 항상 3개의 파일 항목을 표시하도록 반복문 0 ~ 2 까지 3번 반복(배열 인덱스와 동일하게 동작) --%>
						<c:forEach var="i" begin="0" end="2">
							<%-- 각 파일 항목을 구별하기 위해 id 값을 다르게 지정(i값 결합) --%>
							<div id="fileArea${i }">
								<%-- 
								파일이 존재할 경우(arrRealFile 배열 항목이 비어있지 않을 경우) 
								파일명과 삭제 버튼 표시하고, 아니면, 파일 등록 버튼 표시 --%>
								<c:choose>
									<c:when test="${not empty arrRealFile[i] }">
										<c:set var="nameLength" value="${fn:length(arrRealFile[i]) }" />
										<c:set var="indexOf_" value="${fn:indexOf(arrRealFile[i], '_') }" />
										<c:set var="fileName" value="${fn:substring(arrRealFile[i], indexOf_ + 1, nameLength) }" />
										<%-- 컨텍스트 경로/resources/upload 디렉토리 내의 파일 지정 --%>
										<a href="${pageContext.request.contextPath }/resources/upload/${arrRealFile[i] }" download="${fileName }">
											${fileName }
										</a>
										<%-- 삭제 버튼 클릭 시 파일명과 인덱스번호 전달 --%>
										<input type="button" value="삭제" onclick="deleteFile('${arrRealFile[i]}', ${i })">
									</c:when>
									<c:otherwise>
										<input type="file" name="files">
									</c:otherwise>									
								</c:choose>
							</div>
						</c:forEach>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>








