package com.itwillbs.mvc_board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.mvc_board.mapper.BoardMapper;
import com.itwillbs.mvc_board.vo.BoardVO;
import com.itwillbs.mvc_board.vo.InboundProductListVO;
import com.itwillbs.mvc_board.vo.Inbound_Product_ListVO;

@Service
public class BoardService {
	@Autowired
	private BoardMapper mapper;

	// 게시물 등록 작업 수행
	// => 파라미터 : BoardVO 객체    리턴타입 : int(insertCount)
	public int registBoard(BoardVO board) {
		return mapper.insertBoard(board);
	}

	// 게시물 목록 조회
	// => 파라미터 : 검색타입, 검색어, 시작행번호, 목록갯수   
	// => 리턴타입 : List<BoardVO>(boardList)
	public List<BoardVO> getBoardList(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectBoardList(searchType, keyword, startRow, listLimit);
	}

	// 전체 게시물 수 조회
	// => 파라미터 : 검색타입, 검색어   리턴타입 : int(listCount)
	public int getBoardListCount(String searchType, String keyword) {
		return mapper.selectBoardListCount(searchType, keyword);
	}

	// 게시물 상세 정보 조회
	// => 파라미터 : 글번호    리턴타입 : BoardVO(board)
	public BoardVO getBoard(int board_num) {
		return mapper.selectBoard(board_num);
	}

	// 조회수 증가
	// => 파라미터 : 글번호
	public void increaseReadcount(int board_num) {
		mapper.updateReadcount(board_num);
	}

	// 게시물의 패스워드 일치여부 판별
	// => 파라미터 : 글번호, 패스워드    리턴타입 : BoardVO
	public BoardVO isBoardWriter(int board_num, String board_pass) {
		return mapper.selectBoardWriter(board_num, board_pass);
	}

	// 게시물 파일명 조회
	// => 파라미터 : 글번호    리턴타입 : String(realFile)
	public String getRealFile(int board_num) {
		return mapper.selectRealFile(board_num);
	}
	
	// 게시물 삭제
	// => 파라미터 : 글번호    리턴타입 : int(deleteCount)
	public int removeBoard(int board_num) {
		return mapper.deleteBoard(board_num);
	}

	// 게시물 수정
	// => 파라미터 : BoardVO 객체, 리턴타입 : int
	public int modifyBoard(BoardVO board) {
		// 새 업로드 될 파일명을 별도의 파라미터로 추가하여 전달
		// => selectKey 를 통해 기존 파일명을 BoardVO 객체에 덮어쓰기 때문
		return mapper.updateBoard(board, board.getBoard_real_file());
	}

	// 게시물 답글 등록
	public int registReplyBoard(BoardVO board) {
		// 기존 답글들의 순서 번호 조정을 위해 updateBoardReSeq() 메서드 호출
		// => 파라미터 : BoardVO 객체
		mapper.updateBoardReSeq(board);
		
		// 답글 등록 작업을 위해 insertReplyBoard() 메서드 호출
		// => 파라미터 : BoardVO 객체, 리턴타입 : int
		return mapper.insertReplyBoard(board);
	}

	// 게시물 수정 작업 중 개별 파일 삭제
	// => 파라미터 : 글번호, 파일명  리턴타입 : int
	public int removeBoardFile(int board_num, String fileName) {
		return mapper.deleteBoardFile(board_num, fileName);
	}

	// -------------------------------------------------------------
	// JSON 데이터 요청에 대한 처리
	public List<BoardVO> selectBoardList2(List<BoardVO> boardList) {
		return mapper.selectBoardList2(boardList);
	}
	
}











