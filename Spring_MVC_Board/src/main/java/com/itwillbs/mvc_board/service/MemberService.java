package com.itwillbs.mvc_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.mvc_board.mapper.MemberMapper;
import com.itwillbs.mvc_board.vo.MemberVO;

// 서비스 클래스 역할을 수행하기 위한 클래스는 @Service 어노테이션을 지정
@Service
public class MemberService {
	// MyBatis 를 통해 SQL 구문 처리를 담당할 
	// XXXMapper.xml 파일과 연동되는 XXXMapper 객체 자동 주입 설정
	@Autowired
	private MemberMapper mapper;
	
	// 회원 가입 joinMember() 메서드
	// => 파라미터 : MemberVO 객체   리턴타입 : int
	public int joinMember(MemberVO member) {
		// Mapper 객체의 메서드를 호출하여 SQL 구문 실행 요청(DAO 객체 없이 실행)
		// => Mapper 객체의 메서드 실행 후 리턴되는 값을 직접 return 문에 사용하도록
		//    메서드 호출 코드 자체를 return 문 뒤에 바로 기술(리턴값이 없을 경우 호출만 수행)
		// => 단, 메서드 호출 후에도 추가 작업이 필요한 경우 호출문과 리턴문을 분리
		return mapper.insertMember(member);
	}

}















