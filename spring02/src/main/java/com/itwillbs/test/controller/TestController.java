package com.itwillbs.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.test.vo.MemberVo;
import com.itwillbs.test.vo.PersonVO;
import com.itwillbs.test.vo.TestVO;

@Controller
public class TestController {
	
	@GetMapping(value = "main")
	public String requsetMain() {
		return "main"; //Dispatch 방식
	}
	
//	@GetMapping(value = "push")
//	public String requsetPush(HttpServletRequest request) {
//		request.setAttribute("msg", "hello,world");
//		request.setAttribute("test", new TestVO("제목","내용"));
//		return "push";//Dispatch 방식
//	}
	
	@GetMapping(value = "push")
	public String requsetPush(Model model) {
		model.addAttribute("msg", "Hello World - Model 객체");
		model.addAttribute("test", new TestVO("제목 - Model 객체", "내용 - Model 객체"));
		return "push";
	}
	
//	@GetMapping(value = "redirect")
//	public String requsetRedirect() {
//		
//		return "redirect:/redirectServlet";
//	}
//	
//	@GetMapping(value = "redirectServlet")
//	public String redirectServlet() {
//		return "redirect";
//	}
	
//	@GetMapping(value = "redirect")
//	public String requsetRedirect() {
//		String name = "hong";
//		int age = 20;
//		
//		return "redirect:/redirectServlet?name="+name+"&age="+age;
//	}
	
	
	@GetMapping(value = "redirect")
	public String requsetRedirect() {
		String name = "hong";
		int age = 20;
		
		return "redirect:/redirectServlet?name="+name;
	}
	
	
	@GetMapping(value = "redirectServlet")
	public String redirectServlet(@RequestParam(defaultValue = "")String name, @RequestParam(defaultValue = "0")int age) {
		System.out.println(name+","+age);
		return "redirect";
	}
	
	@GetMapping(value = "mav")
	public ModelAndView model_and_view() {
		Map<String, PersonVO> map = new HashMap<String,PersonVO>();
		map.put("person", new PersonVO("홍길동",20));
		Map<String, TestVO> map2 = new HashMap<String,TestVO>();
		map2.put("test", new TestVO("제목","내용"));
		
		return new ModelAndView("model_and_view","map",map2);
	}
	

}
