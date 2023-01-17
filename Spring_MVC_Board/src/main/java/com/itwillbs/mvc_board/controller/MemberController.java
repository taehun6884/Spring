package com.itwillbs.mvc_board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.mvc_board.service.MemberService;
import com.itwillbs.mvc_board.vo.MemberVO;

@Controller
public class MemberController {
	// 컨트롤러 클래스가 서비스 클래스에 의존적일 때
	// Service 객체를 직접 생성하지 않고, 자동 주입 기능을 위한 어노테이션 사용 가능
	// => @Inject(자바-플랫폼 공용) 또는 @Autowired(스프링 전용) 어노테이션 사용
	// => 어노테이션 지정 후 자동 주입으로 객체 생성 시 객체를 저장할 클래스 타입 변수 선언
	// => 주의! Service 클래스에 @Service 어노테이션을 필수로 지정해야한다!
	@Autowired
	private MemberService service;
	
	// "/MemberJoinForm.me" 요청에 대해 member/member_join_form.jsp 페이지 포워딩
	// => GET 방식 요청 & Dispatch 방식 포워딩
	@GetMapping(value = "/MemberJoinForm.me")
	public String join() {
		return "member/member_join_form";
	}
	
	// "/MemberJoinPro.me" 요청에 대해 MemberService 객체를 사용한 비즈니스 로직 수행
	// => POST 방식 요청 & Redirect 방식 포워딩
	// => 폼 파라미터로 전달되는 가입 정보를 저장할 VO 타입 변수 선언
	// => 가입 완료 후 이동할 페이지 : 메인페이지
	// => 가입 실패 시 오류 페이지에 메세지 전송을 위해 Model 타입 변수 선언
	@PostMapping(value = "/MemberJoinPro.me")
	public String joinPro(@ModelAttribute MemberVO member, Model model) {
		// ------------------- BCryptPasswordEncoder 객체 활용한 해싱 -------------------
		// 입력받은 패스워드는 암호화(해싱) 필요 => 해싱 후 MemberVO 객체 패스워드에 덮어쓰기
		// => 스프링에서 암호화는 BCryptPasswordEncoder 객체 사용(spring-security-web 라이브러리)
		// BCryptPasswordEncoder 클래스를 활용하여 해싱할 경우 Salting(솔팅)을 통해
		// 동일한 평문(원본 암호)이라도 매번 다른 결과값을 갖는 해싱이 가능하다!
		// 1. BCryptPasswordEncoder 객체 생성
		BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
		// 2. BCryptPasswordEncoder 객체의 encode() 메서드를 호출하여 해싱 결과 리턴
		// => 파라미터 : 평문 암호    리턴타입 : String(해싱된 암호문)
		String securePasswd = passwdEncoder.encode(member.getPasswd());
//		System.out.println("평문 : " + member.getPasswd());
//		System.out.println("암호문 : " + securePasswd);
		// 3. MemberVO 객체의 패스워드에 암호문 저장(덮어쓰기)
		member.setPasswd(securePasswd);
		// ----------------------------------------------------------------------------------
		// MemberService 객체의 joinMember() 메서드 호출
		// => 파라미터 : MemberVO 객체   리턴타입 : int(insertCount)
//		MemberService service = new MemberService();
		// => 인스턴스를 직접 생성해서 사용해도 되지만
		//    @Autowired 어노테이션을 통해 MemberService 객체를 별도로 생성하지 않아도
		//    자동 주입(= 의존 주입 = DI) 되므로 객체를 즉시 사용 가능
		int insertCount = service.joinMember(member);
		
		// 가입 성공/실패에 따른 포워딩 작업 수행
		if(insertCount > 0) { // 성공
			// 메인페이지로 리다이렉트
			return "redirect:/";
		} else { // 실패
			// 오류 메시지 출력 및 이전 페이지로 돌아가는 기능을 공통으로 수행할
			// fail_back.jsp 페이지로 포워딩(Dispatch)
			// => 출력할 메세지를 해당 페이지로 전달
			// => Model 객체를 통해 "msg" 속성명으로 "가입 실패!" 메세지 전달
			model.addAttribute("msg", "가입 실패!");
			return "fail_back";
		}
		
	}
	
	@GetMapping(value = "/MemberLoginForm.me")
	public String login() {
		return "member/member_login_form";
	}
	
}













