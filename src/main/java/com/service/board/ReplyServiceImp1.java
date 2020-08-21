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
	// ��� ���
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) {
		List<ReplyVO> items = replyDao.list(bno ,start, end);
	String userId = (String) session.getAttribute("userId");
	for(ReplyVO vo : items) {
		// ��� ����߿� ��� ����� ���� ���
		if(vo.getSecretReply().equals("y")) {
			if(userId==null) { //��α��� ���¸� ��� ��۷� ó��
				vo.setReplytext("��� ����Դϴ�");
			}else{ //�α��� ������ ���
				String writer = vo.getWriter(); // �Խù� �ۼ��� ����
				String replyer = vo.getReplyer(); // ��� �ۼ��� ����
				// �α����� ����ڰ� �Խù��� �ۼ���X ��� �ۼ��ڵ� X ��д�۷� ó��
				if(!userId.equals(writer) && !userId.equals(replyer)) {
					vo.setReplytext("��� ����Դϴ�");
				}
			}
		}
	}
	return items;
	}
	// ��� �ۼ�
	@Override
	public void create(ReplyVO vo) {
		replyDao.create(vo);
	}
	// ��� ����
	@Override
	public void update(ReplyVO vo) {
	
	}
	// ��� ����
	@Override
	public void delete(Integer rno) {
		
	}
	//��� ����
	public int count(int bno) {
		return replyDao.count(bno);
	}
	
	
	
}
