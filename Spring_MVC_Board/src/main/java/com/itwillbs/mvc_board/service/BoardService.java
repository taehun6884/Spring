package com.itwillbs.mvc_board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.mvc_board.mapper.BoardMapper;
import com.itwillbs.mvc_board.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	private BoardMapper mapper;

	// 게시물 등록 작업 수행
	// => 파라미터 : BoardVO 객체    리턴타입 : int(insertCount)
	public int registBoard(BoardVO board) {
		return mapper.insertBoard(board);
	}

	public List<BoardVO> getBoardList(String keyword, String searchType,int startRow, int listLimit) {
		return mapper.selectBoardList(keyword,searchType,startRow, listLimit);
	}

	public int getBoardListCount(String searchType, String keyword) {
		return mapper.selectBoardListCount(searchType,keyword);
	}

	public BoardVO getBoard(int board_num) {
		return mapper.getBoard(board_num);
	}

	public void increaseReadCount(int board_num) {
		 mapper.increaseReadCount(board_num);
	}

}











