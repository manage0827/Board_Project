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
	
	// 1. 게시글 작성
	@Override
	public void create(BoardVO vo) throws Exception {
		sqlSession.insert("com.model.board.dao.BoardDAO.insertTest", vo);
	}
	// 2. 게시글 상세보기
	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("view", bno);
	}
	// 3. 게시글 수정
	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update("updateArticle", vo);
	}
	// 4. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("deleteArticle", bno);
		
	}
	// 5. 게시글 전체 목록
	@Override
	public List<BoardVO> listAll() throws Exception {
		return sqlSession.selectList("listAll");
	// BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
				 
	}
	// 6. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("increaseViewcnt", bno);
		
	}
	


}
