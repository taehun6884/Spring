package com.itwillbs.mvc_board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.mvc_board.vo.BoardVO;

public interface BoardMapper {

	// 1. 게시물 등록
	// => 파라미터 : BoardVO 객체    리턴타입 : int
	int insertBoard(BoardVO board);
	
	// 게시물 목록 조회
	List<BoardVO> selectBoardList(
		@Param("keyword")String keyword, 
		@Param("searchType")String searchType, 
		@Param("startRow")	int startRow, 
		@Param("listLimit")	int listLimit);

	// 전체 게시물 수 조회
	int selectBoardListCount(
			@Param("keyword")String keyword, 
			@Param("searchType")String searchType);

	BoardVO getBoard(int board_num);

	void increaseReadCount(int board_num);
	
}
