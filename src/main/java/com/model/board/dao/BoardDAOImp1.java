package com.model.board.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.board.dto.BoardVO;
import com.model.board.dto.TestVO;

@Repository
public class BoardDAOImp1 implements BoardDAO{
	
	
	private Logger log = LoggerFactory.getLogger(BoardDAOImp1.class);
	@Autowired
	SqlSession sqlSession;
	
	// 1. �Խñ� �ۼ�
	@Override
	public void create(BoardVO vo) throws Exception {
		sqlSession.insert("com.model.board.dao.BoardDAO.insertTest", vo);
	}
	// 2. �Խñ� �󼼺���
	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("view", bno);
	}
	// 3. �Խñ� ����
	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update("updateArticle", vo);
	}
	// 4. �Խñ� ����
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("deleteArticle", bno);
		
	}
	// 5. �Խñ� ��ü ���
	@Override
	public List<BoardVO> listAll() throws Exception {
		return sqlSession.selectList("listAll");
	// BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
				 
	}
	// 6. �Խñ� ��ȸ�� ����
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("increaseViewcnt", bno);
		
	}
	


}
