package com.service.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.board.dao.MemberDAOImp1;

import com.model.board.dto.MemberVO;

@Service
public class MemberServiceImp1 implements MemberService{

	@Autowired
	MemberDAOImp1 memberDao;
	
	// 01_01. 회원 로그인체크
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
		boolean result = memberDao.loginCheck(vo);
		if(result) { // true 일 경우 세션에 등록
			MemberVO vo2 = viewMember(vo);
			//세션 변수 등록
			session.setAttribute("userId", vo2.getUserId());
			session.setAttribute("userName", vo2.getUserName());
		}
		return result;
	}

	// 01_02. 회원 로그인 정보
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return memberDao.viewMember(vo);
	}

	@Override
	public void logout(HttpSession session) {
		// 세션 변수 개별 삭제
		// session.removeAttribute("userId");
		// 세션 정보를 초기화 시킴
		session.invalidate();
	}

}
