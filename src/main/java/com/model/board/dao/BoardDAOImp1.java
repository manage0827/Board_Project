package com.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.board.dto.BoardVO;


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
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		// 검색 옵션 , 키워드 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		//between #{start}, #{end}에 입력될 값을 맵에 저장
		map.put("start", start);
		map.put("end",end);
		return sqlSession.selectList("listAll",map);
	// BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
				 
	}
	// 6. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("increaseViewcnt", bno);
		
	}
	// 7. 게시글 레코드 갯수
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		// 검색 옵션, 키워드 맵에 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectOne("countArticle",map);
	}
	


}
