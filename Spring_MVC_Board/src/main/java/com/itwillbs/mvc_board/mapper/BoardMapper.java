package com.itwillbs.mvc_board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.mvc_board.vo.BoardVO;
import com.itwillbs.mvc_board.vo.InboundProductListVO;
import com.itwillbs.mvc_board.vo.Inbound_Product_ListVO;

public interface BoardMapper {

	// 1. 게시물 등록
	// => 파라미터 : BoardVO 객체    리턴타입 : int
	int insertBoard(BoardVO board);

	// 2. 게시물 목록 조회
	// => 파라미터 : 검색타입, 검색어, 시작행번호, 목록갯수   
	// => 리턴타입 : List<BoardVO>
	// => 주의! 복수개의 파라미터 지정 시 @Param 어노테이션 필수! (파라미터명 지정)
	List<BoardVO> selectBoardList(
			@Param("searchType") String searchType, 
			@Param("keyword") String keyword, 
			@Param("startRow") int startRow, 
			@Param("listLimit") int listLimit);

	// 3. 전체 게시물 수 조회
	// => 파라미터 : 검색타입, 검색어   리턴타입 : int
	// => 주의! 복수개의 파라미터 지정 시 @Param 어노테이션 필수! (파라미터명 지정)
	int selectBoardListCount(
			@Param("searchType") String searchType, 
			@Param("keyword") String keyword);

	// 4. 게시물 상세 정보 조회
	// => 파라미터 : 글번호    리턴타입 : BoardVO
	BoardVO selectBoard(int board_num);

	// 5. 조회수 증가
	// => 파라미터 : 글번호
	void updateReadcount(int board_num);

	// 6. 게시물의 패스워드 일치여부 판별
	// => 파라미터 : 글번호, 패스워드    리턴타입 : BoardVO
	BoardVO selectBoardWriter(
			@Param("board_num") int board_num, @Param("board_pass") String board_pass);

	// 7. 게시물 파일명 조회
	// => 파라미터 : 글번호    리턴타입 : String
	String selectRealFile(int board_num);

	// 8. 게시물 삭제
	// => 파라미터 : 글번호    리턴타입 : int
	int deleteBoard(int board_num);

	// 9. 게시물 수정
	// => 파라미터 : BoardVO 객체  리턴타입 : int
//	int updateBoard(BoardVO board);
	int updateBoard(@Param("board") BoardVO board, 
					@Param("board_real_file") String board_real_file);

	// 10. 기존 답글들의 순서번호 조정
	// => 파라미터 : BoardVO 객체
	void updateBoardReSeq(BoardVO board);

	// 11. 답글 등록
	// => 파라미터 : BoardVO 객체  리턴타입 : int
	int insertReplyBoard(BoardVO board);

	// 12. 개별 파일 삭제
	// => 파라미터 : 글번호, 파일명  리턴타입 : int
	int deleteBoardFile(@Param("board_num") int board_num, @Param("fileName") String fileName);

	// -------------------------------------------------------------
	// JSON 데이터 요청에 대한 처리
	List<BoardVO> selectBoardList2(List<BoardVO> boardList);
	
	
}










