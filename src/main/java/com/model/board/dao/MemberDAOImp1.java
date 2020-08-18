package com.model.board.dao;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.board.dto.MemberVO;
@Repository //���� Ŭ������ ���������� �����ϴ� dao bean ���� ���
public class MemberDAOImp1 implements MemberDAO{
	//SqlSession ��ü�� ���������� �����Ͽ� ����
	//�������� ����, ������ ����
	@Autowired
	SqlSession sqlSession; // mybatis ���� ��ü
	
	// 01_01. ȸ�� �α���üũ
	@Override
	public boolean loginCheck(MemberVO vo) {
		String name = sqlSession.selectOne("loginCheck", vo);
		return (name == null) ? false : true;
	}
	// 01_02. ȸ�� �α��� ����
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return sqlSession.selectOne("viewMember", vo);
	}

	@Override
	public void logout(HttpSession session) {
		;
		
	}

}
