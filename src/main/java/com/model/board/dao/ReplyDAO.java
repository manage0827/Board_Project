package com.model.board.dao;

import java.util.List;

import com.model.board.dto.ReplyVO;

public interface ReplyDAO {

	// ´ñ±Û ¸ñ·Ï
	public List<ReplyVO> list(Integer bno, int start, int end);
	// ´ñ±Û ÀÔ·Â
	public void create(ReplyVO vo);
	// ´ñ±Û ¼öÁ¤
	public void update(ReplyVO vo);
	// ´ñ±Û »èÁ¦
	public void delete(Integer rno);
	// ´ñ±Û °¹¼ö
	public int count(int bno);
}
