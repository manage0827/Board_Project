package com.model.board.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.model.board.dto.BoardVO;
import com.model.board.dto.TestVO;


@Repository
public interface TestDAO {

	@Insert("insert into tbl_board(bno,title,content,writer) values(#{bno}, #{title}, #{content}, #{writer})")
	public void testInsert(BoardVO boardVO);
	
}
