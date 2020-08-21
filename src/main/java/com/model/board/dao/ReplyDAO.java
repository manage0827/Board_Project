package com.model.board.dao;

import java.util.List;

import com.model.board.dto.ReplyVO;

public interface ReplyDAO {

	// ��� ���
	public List<ReplyVO> list(Integer bno, int start, int end);
	// ��� �Է�
	public void create(ReplyVO vo);
	// ��� ����
	public void update(ReplyVO vo);
	// ��� ����
	public void delete(Integer rno);
	// ��� ����
	public int count(int bno);
}
