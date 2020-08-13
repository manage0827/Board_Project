package com.Board.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.model.board.dao.BoardDAOImp1;
import com.model.board.dao.TestDAO;
import com.model.board.dto.BoardVO;
import com.model.board.dto.TestVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DAOTest {
	
	
	
	  @Autowired 
	  BoardDAOImp1 board;
	

	@Autowired
	TestDAO test;

	@Test
	public void test() throws Exception {

		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1);
		boardVO.setTitle("myBatis");
		boardVO.setContent("Hello world");
		boardVO.setWriter("okokwdqw");
		
		board.create(boardVO);
	
	}

}
