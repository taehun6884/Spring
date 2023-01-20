package com.itwillbs.mvc_board.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.mvc_board.service.BoardService;
import com.itwillbs.mvc_board.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("/BoardWriteForm.bo")
	public String write() {
		return "board/qna_board_write";
	}
	
	// 글쓰기 비즈니스 로직 - 파일 업로드 기능 추가
	// => commons-io, commons-fileupload 라이브러리 추가
	@PostMapping("/BoardWritePro.bo")
	public String writePro(@ModelAttribute BoardVO board, Model model, HttpSession session) {
		// 세션 아이디가 존재하지 않으면 "로그인 필수!" 출력하고 이전페이지로 이동시키기
		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("")) {
			model.addAttribute("msg", "로그인 필수!");
		}
		
		// 주의! 파일 업로드 기능을 통해 전달받은 객체를 다룰 때
		// MultipartFile 타입 클래스 활용할 경우
		// BoardVO 클래스 내에 MultipartFile 타입 변수와 Getter/Setter 정의 필수
		// => 이 때, 변수명은 input type="file" 태그의 name 속성과 동일한 변수명 사용
		String uploadDir = "/resource/upload";
		
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
//		System.out.println("실제 업로드 경로:"+saveDir);
	
		//실제 경로를 갖는 file 객체 생성
		File f = new File(saveDir);
		
		if(!f.exists()) {
//			f.mkdir();
			f.mkdirs();
		}
		
		MultipartFile[] mFiles = board.getFiles();
		
//		MultipartFile mFile = board.getBoard_file();
		
		String originalFileNames="";
		String realFileNames="";
		
		for(MultipartFile mFile : mFiles) {
			String originalFileName = mFile.getOriginalFilename();
			long fileSize = mFile.getSize();
//			System.out.println("파일명: "+ originalFileName);
//			System.out.println("파일크기: "+fileSize+"Byte");
			String uuid = UUID.randomUUID().toString();
			System.out.println("업로드 될 파일명 : "+uuid +"_"+originalFileName);
			
			originalFileNames += originalFileName + "/";
			
		}
		
		board.setBoard_file(realFileNames);
		board.setBoard_real_file(realFileNames);
		System.out.println("원본 파일명 : "+board.getBoard_file());
		System.out.println("업로드 될 파일 명 :"+board.getBoard_real_file());
		int insertCount = service.registBoard(board);
		
		if(insertCount > 0) { // 성공
			for(int i=0;i<board.getFiles().length;i++) {
				MultipartFile mFile = board.getFiles()[i];
				mFile.transferTo(new File(saveDir,board.getBoard_real_file()));
			}
			return "redirect:/BoardList.bo";
		} else { // 실패
			// 오류 메시지 출력 및 이전 페이지로 돌아가는 기능을 공통으로 수행할
			// fail_back.jsp 페이지로 포워딩(Dispatch)
			// => 출력할 메세지를 해당 페이지로 전달
			// => Model 객체를 통해 "msg" 속성명으로 "가입 실패!" 메세지 전달
			model.addAttribute("msg", "가입 실패!");
			return "fail_back";
		}
	}
}















