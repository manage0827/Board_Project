package com.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.board.dao.BoardDAO;
import com.model.board.dao.BoardDAOImp1;
import com.model.board.dto.BoardVO;

@Service
public class BoardServiceImp1 implements BoardService {

	@Autowired
	BoardDAOImp1 boardDao;
	
	// 01. 게시글 쓰기
	@Override
	public void create(BoardVO vo) throws Exception {
		boardDao.create(vo);
	}
	
	// 02. 게시글 상세보기
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.read(bno);
	}
	// 03. 게시글 수정
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);
		
	}
	// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
		
	}
	// 05. 게시글 전체 목록
	@Override
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception {
		return boardDao.listAll(searchOption, keyword);
	}
	// 06. 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		boardDao.increaseViewcnt(bno);	
	}
	// 07. 게시글 레코드 갯수 board.Dao.countArticle 메서드
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		return boardDao.countArticle(searchOption, keyword);
	}

}
