package com.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.board.dao.BoardDAOImp1;
import com.model.board.dto.BoardVO;
import com.service.board.BoardService;
import com.service.board.BoardServiceImp1;

@Controller // 현재클래스를 컨트롤러 bean으로 등록
public class BoardController {
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	// 의존관계 주입 => BoardServiceImp1 생성
	// IOC 의존관계 역전
	@Autowired
	BoardServiceImp1 boardService;

	@RequestMapping("/")
	public String home() {
		return "redirect:list";
	}

	// 01.게시글 목록
	/*
	 * @RequestMapping("/list") public ModelAndView list() throws Exception {
	 * List<BoardVO> list = boardService.listAll(); // ModelAndView - 모델과 뷰
	 * ModelAndView mav = new ModelAndView(); mav.setViewName("list"); // 뷰를
	 * list.jsp로 설정 mav.addObject("list", list); // 데이터를 저장 return mav; // list.jsp로
	 * List가 전달된다. }
	 */
	@RequestMapping(value="list")
	public String list(Model model, @RequestParam(defaultValue="title") String searchOption,
						@RequestParam(defaultValue="") String keyword) throws Exception {
		List<BoardVO> list = (List<BoardVO>)boardService.listAll(searchOption, keyword);
		model.addAttribute("list", list);
		int count = boardService.countArticle(searchOption, keyword);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); //list
		map.put("count", count); //레코드의 갯수
		map.put("searchOption", searchOption); // 검색옵션
		map.put("keyword", keyword); //검색키워드
		model.addAttribute("map", map); // 맵에 저장된 데이터를 저장
		return "list"; // list.jsp로 List가 전달된다.
	}

	// 02_01. 게시글 작성화면
	// @RequestMapping("board/write.do")
	// value="", method="전송방식"
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String write() {
		return "write"; // write.jsp로 이동
	}

	// 02_02. 게시글 작성처리
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception {
		// session에 저장된 userId를 writer에 저장
		String writer = (String) session.getAttribute("userId");
		// vo 에 writer를 세팅
		vo.setWriter(writer);
		boardService.create(vo);
		return "redirect:list";
	}

	// 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
	// @RequestParam : get/post 방식으로 전달된 변수 1개
	// HttpSession 세션객체
	@RequestMapping(value = "view")
	public ModelAndView view(int bno) throws Exception {
		boardService.increaseViewcnt(bno); 
		// 모델(데이터)+뷰(화면)를 함께 전달하는 객체
		ModelAndView mav = new ModelAndView();
		// 뷰의 이름
		mav.setViewName("/view");
		// 뷰에 전달할 데이터
		mav.addObject("dto", boardService.read(bno));
		return mav;
	}

	// 04. 게시글 수정
	// 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		boardService.update(vo);
		return "redirect:list";
	}

	// 05. 게시글 삭제
	@RequestMapping(value="delete", method= RequestMethod.POST)
	public String delete(@ModelAttribute BoardVO vo) throws Exception {
		boardService.delete(vo.getBno());
		return "redirect:list";
	}
	
	

}
