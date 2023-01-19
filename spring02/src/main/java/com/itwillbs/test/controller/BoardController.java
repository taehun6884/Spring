package com.itwillbs.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.test.vo.BoardVO;

@Controller
public class BoardController {
// 	글쓰기	
	@GetMapping(value = "write.bo")
	public String write() {
		return "qna_board_write";
	}
	
// 
	@PostMapping(value = "write.bo")
	public String writePro(@ModelAttribute BoardVO vo, HttpServletRequest request) {
		System.out.println(vo);
		return "redirect:/list.bo";
	}
	
	@GetMapping(value ="list.bo")
	public String list(@ModelAttribute BoardVO vo,Model model) {
		System.out.println(vo.toString());
		
		List<BoardVO> boardlist= new ArrayList<BoardVO>();
		
		boardlist.add(new BoardVO(1,"관리자","1234","제목1","내용1","","",1,0,0,0,null));
		boardlist.add(new BoardVO(2,"관리자","1234","제목2","내용2","","",2,0,0,0,null));
		boardlist.add(new BoardVO(3,"관리자","1234","제목3","내용3","","",3,0,0,0,null));
		
		model.addAttribute("boardList",boardlist);
		return "qna_board_list";
	}
	
	
}
