package com.model.board.dao;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.board.dto.MemberVO;
@Repository //현재 클래스를 스프링에서 관리하는 dao bean 으로 등록
public class MemberDAOImp1 implements MemberDAO{
	//SqlSession 객체를 스프링에서 생성하여 주입
	//의존관계 주입, 느슨한 결합
	@Autowired
	SqlSession sqlSession; // mybatis 실행 객체
	
	// 01_01. 회원 로그인체크
	@Override
	public boolean loginCheck(MemberVO vo) {
		String name = sqlSession.selectOne("loginCheck", vo);
		return (name == null) ? false : true;
	}
	// 01_02. 회원 로그인 정보
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return sqlSession.selectOne("viewMember", vo);
	}

	@Override
	public void logout(HttpSession session) {
		;
		
	}

}
