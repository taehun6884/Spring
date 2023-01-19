package com.itwillbs.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.test.vo.MemberVo;

@Controller
public class MemberController {
	@GetMapping(value = "login.me")
	public String login() {
		return "member_login_form_sample";
	}
	
//	@PostMapping(value = "login.me")
//	public String loginPro() {
//		System.out.println("loginPro");
//		return "";
//	}
	
//	@PostMapping(value = "login.me")
//	public String login(@RequestParam String id, String passwd) {
//		System.out.println("아이디 : "+id);
//		System.out.println("패스워드 : "+passwd);
//		
//		return"redirect:/main";
//	}
	
// ------------------------------------------------------------------------
	
	
//	@PostMapping(value = "login.me")
//	public String login(@RequestParam Map<String,String> params) {
//		System.out.println("아이디(id) : "+ params.get("id"));
//		System.out.println("패스워드(passwd) : "+ params.get("passwd"));
//		
//		return"redirect:/main";
//	}
	
//	@PostMapping(value = "login.me")
//	public String login(@ModelAttribute MemberVo member) {
//		System.out.println("아이디(id) : "+ member.getId());
//		System.out.println("패스워드(passwd) : "+ member.getPasswd());
//		
//		
//		return"redirect:/main";
//	}
	
// 	로그인
	@PostMapping(value = "login.me")
	public String login(@ModelAttribute MemberVo member, HttpSession session) {
		System.out.println("아이디(id) : "+ member.getId());
		System.out.println("패스워드(passwd) : "+ member.getPasswd());
		session.setAttribute("sId", member.getId());
		return"redirect:/main";
	}
// 로그아웃	
	@GetMapping(value = "logout.me")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return"redirect:/main";
	}
	
}
