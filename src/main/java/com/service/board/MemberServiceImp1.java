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
	
	// 01_01. ȸ�� �α���üũ
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
		boolean result = memberDao.loginCheck(vo);
		if(result) { // true �� ��� ���ǿ� ���
			MemberVO vo2 = viewMember(vo);
			//���� ���� ���
			session.setAttribute("userId", vo2.getUserId());
			session.setAttribute("userName", vo2.getUserName());
		}
		return result;
	}

	// 01_02. ȸ�� �α��� ����
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return memberDao.viewMember(vo);
	}

	@Override
	public void logout(HttpSession session) {
		// ���� ���� ���� ����
		// session.removeAttribute("userId");
		// ���� ������ �ʱ�ȭ ��Ŵ
		session.invalidate();
	}

}
