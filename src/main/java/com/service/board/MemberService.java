package com.service.board;

import javax.servlet.http.HttpSession;
import com.model.board.dto.MemberVO;
public interface MemberService {
	// 01_01. ȸ�� �α��� üũ
	public boolean loginCheck(MemberVO vo, HttpSession session);
	// 01_02. ȸ�� �α��� ����
	public MemberVO viewMember(MemberVO vo);
	// 02. ȸ�� �α׾ƿ�
	public void logout(HttpSession session);
}
