package com.controller.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.board.dto.ReplyVO;
import com.service.board.ReplyPager;
import com.service.board.ReplyServiceImp1;

// REST : Representational State Transfer
// 하나의 URI 가 하나의 고유한 리소스를 대표하도록 설계된 개념

// http://localhost/board/list?bno=1 ==> url+파라미터
// http://localhost/board/list/1 ==> url
// RestController 은 스프링 4.0부터 지우너
// @Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무
// @Controller
@RestController
@RequestMapping(value="reply")
public class ReplyController {
	Logger log = LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	ReplyServiceImp1 replyService;
	
	//댓글 입력
	@RequestMapping(value = "insert", method = RequestMethod.POST )
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) {
		log.info(vo.toString());
		String userId = (String) session.getAttribute("userId");
		vo.setReplyer(userId);
		replyService.create(vo);
		
	}
	// 댓글입력 (Rest 방식으로 json전달하여 글쓰기)
	// @ResponseEntity : 데이터 + http status code
	// @ResponseBody : 객체를 json으로 (json-> String)
	// @RequestBody : json을 객체로
	/*
	 * @RequestMapping(value="insertRest", method = RequestMethod.POST) public
	 * ResponseEntity<String> insertRest(@RequestBody ReplyVO vo, HttpSession
	 * session){ ResponseEntity<String> entity = null; try { String userId =
	 * (String) session.getAttribute("userId"); vo.setReplyer(userId);
	 * replyService.create(vo); // 댓글입력이 성공하면 성공메시지 저장 entity = new
	 * ResponseEntity<String>("success", HttpStatus.OK); } catch(Exception e) {
	 * e.printStackTrace(); // 댓글 입력이 실패하면 실패메시지 저장 entity = new
	 * ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); } // 입력 처리
	 * HTTTP 상태 메시지 리턴 return entity; }
	 */
	

	// 댓글 목록(@Contorller방식 : view(화면)를 리턴
	@RequestMapping(value ="replyList")
	public String list(Model model, @RequestParam int bno,
						@RequestParam(defaultValue="1")int curPage, 
						HttpSession session) {
		 // 페이징 처리
		  int count = replyService.count(bno); // 댓글 갯수
		  
		  ReplyPager replyPager = new ReplyPager(count, curPage); 
		  int start =replyPager.getPageBegin(); 
		  int end = replyPager.getPageEnd(); 
		  List<ReplyVO>list = replyService.list(bno, start, end, session); // 뷰에 전달할 데이터 지정
		  model.addAttribute("list", list); 
		  model.addAttribute("replyPager"); // replyList.jsp로 포워딩
		 	 	return "replyList";
	}
	
	
	  // 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
	/*
	 * @RequestMapping(value="listJson", method = RequestMethod.POST)
	 * 
	 * @ResponseBody // 리턴데이터를 json으로 변환(생략가능) public List<ReplyVO>
	 * listJson(@RequestParam int bno){ List<ReplyVO> list = replyService.list(bno,
	 * start, end, session); return list; }
	 */
	 
}
