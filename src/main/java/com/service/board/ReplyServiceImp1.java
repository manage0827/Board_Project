package com.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.board.dao.ReplyDAOImp1;
import com.model.board.dto.ReplyVO;

@Service
public class ReplyServiceImp1 implements ReplyService{

	@Autowired
	ReplyDAOImp1 replyDao;
	// 댓글 목록
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) {
		List<ReplyVO> items = replyDao.list(bno ,start, end);
	String userId = (String) session.getAttribute("userId");
	for(ReplyVO vo : items) {
		// 댓글 목록중에 비밀 댓글이 있을 경우
		if(vo.getSecretReply().equals("y")) {
			if(userId==null) { //비로그인 상태면 비밀 댓글로 처리
				vo.setReplytext("비밀 댓글입니다");
			}else{ //로그인 상태일 경우
				String writer = vo.getWriter(); // 게시물 작성자 저장
				String replyer = vo.getReplyer(); // 댓글 작성자 저장
				// 로그인한 사용자가 게시물의 작성자X 댓글 작성자도 X 비밀댓글로 처리
				if(!userId.equals(writer) && !userId.equals(replyer)) {
					vo.setReplytext("비밀 댓글입니다");
				}
			}
		}
	}
	return items;
	}
	// 댓글 작성
	@Override
	public void create(ReplyVO vo) {
		replyDao.create(vo);
	}
	// 댓글 수정
	@Override
	public void update(ReplyVO vo) {
	
	}
	// 댓글 삭제
	@Override
	public void delete(Integer rno) {
		
	}
	//댓글 갯수
	public int count(int bno) {
		return replyDao.count(bno);
	}
	
	
	
}
