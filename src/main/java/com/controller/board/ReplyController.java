package com.controller.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.board.dto.ReplyVO;
import com.service.board.ReplyPager;
import com.service.board.ReplyServiceImp1;

// REST : Representational State Transfer
// �ϳ��� URI �� �ϳ��� ������ ���ҽ��� ��ǥ�ϵ��� ����� ����

// http://localhost/board/list?bno=1 ==> url+�Ķ����
// http://localhost/board/list/1 ==> url
// RestController �� ������ 4.0���� �����
// @Controller, @RestController �������� �޼��尡 ����Ǹ� ȭ����ȯ�� ����
@Component("replyController")
@RestController
@RequestMapping(value="/reply")
public class ReplyController {
	Logger log = LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	ReplyServiceImp1 replyService;
	
	//��� �Է�
	@RequestMapping(value = "insert", method = RequestMethod.POST )
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) {
		log.info(vo.toString());
		String userId = (String) session.getAttribute("userId");
		vo.setReplyer(userId);
		replyService.create(vo);
		
	}
	// ����Է� (Rest ������� json�����Ͽ� �۾���)
	// @ResponseEntity : ������ + http status code
	// @ResponseBody : ��ü�� json���� (json-> String)
	// @RequestBody : json�� ��ü��
	
	  @RequestMapping(value="insertRest", method = RequestMethod.POST) 
	  public ResponseEntity<String> insertRest(@RequestBody ReplyVO vo, HttpSession session){ 
		  ResponseEntity<String> entity = null; 
		  try { 
			  String userId =(String) session.getAttribute("userId"); 
			  vo.setReplyer(userId);
			  replyService.create(vo); 
			  // ����Է��� �����ϸ� �����޽��� ���� entity = new
			  entity = new ResponseEntity<String> ("success", HttpStatus.OK); 
			  } 
		  catch(Exception e) {
			e.printStackTrace();
			// ��� �Է��� �����ϸ� ���и޽��� ���� entity = new
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); 
			} 
		  // �Է� ó�� HTTTP ���� �޽��� ���� 
		  	return entity; 
		  	}
	 
	

	// ��� ���(@Contorller��� : view(ȭ��)�� ����
			
			  @RequestMapping(value ="/replyList") 
			  public ResponseEntity<List<ReplyVO>> list(Model model, @RequestParam int bno,
			  @RequestParam(defaultValue="1")int curPage, HttpSession session) { 
			 
			  // ����¡ ó��
			  int count = replyService.count(bno); // ��� ����
			  ReplyPager replyPager = new ReplyPager(count, curPage); 
			  int start =replyPager.getPageBegin(); 
			  int end = replyPager.getPageEnd();
			  List<ReplyVO> list = replyService.list(bno, start, end, session);
			  
			  // �信 ������ ������ ����
			  model.addAttribute("list", list); 
			  model.addAttribute("replyPager",replyPager); 
			  // replyList.jsp�� ������ 
			  return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK); 
			  }
			  
	
	
	  //��� ���(@RestController Json������� ó�� : �����͸� ����)
			/*
			 * @RequestMapping(value="listJson", method = RequestMethod.POST)
			 * 
			 * @ResponseBody // ���ϵ����͸� json���� ��ȯ(��������) public List<ReplyVO>
			 * listJson(@RequestParam int bno, @RequestParam(defaultValue="1") int curPage){
			 * int count=10; BoardPager pager = new BoardPager(count, curPage); int start =
			 * pager.getPageBegin(); int end = pager.getPageEnd(); List<ReplyVO> list =
			 * replyService.list(bno, start, end, session);
			 * 
			 * }
			 */
}
