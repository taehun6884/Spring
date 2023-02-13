<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%-- EL 에서 표기 방식(날짜 등)을 변경하려면 fmt(format) 라이브러리 필요  --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<!-- 외부 CSS 가져오기 -->
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
	}
	
	#tr_top {
		background: orange;
		text-align: center;
	}
	
	table td {
		text-align: center;
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
		margin-top: 10px;
	}
	
	a {
		text-decoration: none;
	}
</style>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	// AJAX 를 활용한 게시물 목록 표시에 사용될 페이지 번호값 미리 저장
	let pageNum = 1;
	
	$(function() {
		// 검색타입(searchType)과 검색어(keyword) 값 가져와서 변수에 저장
		let searchType = $("#searchType").val();
		let keyword = $("#keyword").val();
// 		alert(searchType + ", " + keyword);
		
		// 게시물 목록 조회를 처음 수행하기 위해 load_list() 함수 호출
		// => 검색타입과 검색어를 파라미터로 전달(검색하지 않을 경우에도 동일) 
		load_list(searchType, keyword);
		
		// 무한스크롤 기능 구현(임시로 삭제함)
	});
	
	// 게시물 목록 조회를 AJAX + JSON 으로 처리할 load_list() 함수 정의
	// => 검색타입과 검색어를 파라미터로 지정
	function load_list(searchType, keyword) {
		$.ajax({
			type: "GET",
// 			url: "BoardListJson.bo?pageNum=" + pageNum,
			url: "BoardListJson.bo?pageNum=" + pageNum + "&searchType=" + searchType + "&keyword=" + keyword,
			dataType: "json"
		})
		.done(function(boardList) { // 요청 성공 시
// 			$("#listForm > table").append(boardList);
			
		
			// JSONArray 객체를 통해 배열 형태로 전달받은 JSON 데이터를
			// 반복문을 통해 하나씩 접근하여 객체 꺼내기
			// 반복문 내에서 인덱스 번호로 활용할 변수 선언
			let index = 0;
			
			for(let board of boardList) {
				// 답글일 경우 제목 앞 공백 추가 및 답글 아이콘 추가 작업
				let space = "";
				
				// board 객체의 board_re_lev 값이 0 보다 크면
				// 제목열에 해당 값만큼 공백(&nbsp;) 추가 후 
				// 공백 뒤에 답글 아이콘 이미지(re.gif) 추가
				if(board.board_re_lev > 0) {
					for(let i = 0; i < board.board_re_lev; i++) {
						space += "&nbsp;&nbsp;"; // lev 값 1 당 공백 2칸 추가
					}
					
					space += "<img src='${pageContext.request.contextPath}/resources/images/re.gif'>&nbsp;";
				}
				
				// 테이블에 표시할 JSON 데이터 출력문 생성
				// => 각 게시물(레코드) 구별을 위해 id 값을 다르게 생성
				let result = "<tr>"
							+ "<td><input type='checkbox' class='jsonCheck' id='check" + index + "' value='" + index + "'></td>"
							+ "<td id='board_num" + index + "'>" + board.board_num + "</td>"
							+ "<td id='subject'>" 
								+ space
								+ "<a href='BoardDetail.bo?board_num=" + board.board_num + "' id='board_subject" + index + "'>"
								+ board.board_subject + "</a></td>"
							+ "<td id='board_name" + index + "'>" + board.board_name + "</td>"
							+ "<td>" + board.board_date + "</td>"
							+ "<td>" + board.board_readcount + "</td>"
							+ "</tr>";
				
				// 지정된 위치(table 태그 내부)에 JSON 객체 출력문 추가
				$("#listForm > table").append(result);
				
				
				// 인덱스 1 증가
				index++; 
			}
		})
		.fail(function() {
			$("#listForm > table").append("<h3>요청 실패!</h3>");
		});
		
		$("#btnOk").click(function() {
			// 객체를 저장할 배열 생성
			let boardArr = new Array();
			
			// .jsonCheck 선택자에 해당하는 체크박스 갯수만큼 반복
			$(".jsonCheck").each(function(index, item) {
// 				console.log(index + " : " + item);
// 				console.log(index + " : " + item.checked);

				// 체크박스의 상태가 true 일 때 체크박스 인덱스와 동일한 인덱스를 갖는
				// 데이터들을 가져와서 객체로 생성 후 객체를 배열에 추가(push)
				if(item.checked) {
// 					console.log(index + " : " + item.checked);
					// 데이터들을 저장할 객체 생성
					let board = new Object();
					
					// 각 요소의 내부 데이터(텍스트 요소) 가져와서 객체에 저장
					// => html() 함수가 아닌 text() 함수를 사용하여 텍스트 요소만 가져오기
					board.board_num = $("#board_num"+ index).text();
					board.board_name = $("#board_name"+ index).text();
					board.board_subject = $("#board_subject"+ index).text();
					
// 					console.log(board);

					// 생성된 객체 1개를 배열에 추가
					boardArr.push(board);
				}
					 
			}); // each() 함수 끝
			
// 			console.log(boardArr);
// 			console.log(JSON.stringify(boardArr));
			
			// AJAX 요청을 통해 JSON 데이터 전송
			$.ajax({
				type: "POST",
				url: "BoardJson.bo",
				dataType: "text", // 응답 데이터 타입
				contentType: "application/json", // 요청 시 전송 데이터 타입
				data: JSON.stringify(boardArr),
				success: function(result) {
					alert(result);
				}, 
				fail: function() {
					alert("요청 실패!");
				}
			});
			
		});
		
	}
	
	
</script>
</head>
<body>
	<header>
		<!-- Login, Join 링크 표시 영역 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 리스트 -->
	<section id="listForm">
		<h2>게시판 글 목록</h2>
		<section id="buttonArea">
			<form action="BoardList.bo">
				<!-- 검색 타입 추가 -->
				<select name="searchType" id="searchType">
					<option value="subject" <c:if test="${param.searchType eq 'subject'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${param.searchType eq 'content'}">selected</c:if>>내용</option>
					<option value="subject_content" <c:if test="${param.searchType eq 'subject_content'}">selected</c:if>>제목&내용</option>
					<option value="name" <c:if test="${param.searchType eq 'name'}">selected</c:if>>작성자</option>
				</select>
				<input type="text" name="keyword" id="keyword" value="${param.keyword }">
				<input type="submit" value="검색">
				&nbsp;&nbsp;
				<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.bo'" />
			</form>
		</section>
		<table>
			<tr id="tr_top">
				<td>선택</td>
				<td width="100px">번호</td>
				<td>제목</td>
				<td width="150px">작성자</td>
				<td width="150px">날짜</td>
				<td width="100px">조회수</td>
			</tr>
			<!-- AJAX 를 사용하여 글목록 조회 결과를 표시할 위치 -->
		</table>
	</section>
	<div id="hiddenArea">
		<input type="button" value="확인" id="btnOk">
	</div>
</body>
</html>













