package com.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.board.dto.ReplyVO;

@Repository
public class ReplyDAOImp1 implements ReplyDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	// ��� ���
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("reply.listReply", map);
	}
	// ��� �ۼ�
	@Override
	public void create(ReplyVO vo) {
		sqlSession.insert("reply.insertReply", vo);
	}
	// ��� ����
	@Override
	public void update(ReplyVO vo) {
		
	}
	// ��� ����
	@Override
	public void delete(Integer rno) {
		
	}
	// ��� ����
	@Override
	public int count(int bno) {
		return sqlSession.selectOne("reply.countReply", bno);
	}

}
