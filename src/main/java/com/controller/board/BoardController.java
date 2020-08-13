package com.controller.board;

import java.util.List;

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

@Controller // ����Ŭ������ ��Ʈ�ѷ� bean���� ���
public class BoardController {
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	// �������� ���� => BoardServiceImp1 ����
	// IOC �������� ����
	@Autowired
	BoardServiceImp1 boardService;

	@RequestMapping("/")
	public String home() {
		return "redirect:list";
	}

	// 01.�Խñ� ���
	/*
	 * @RequestMapping("/list") public ModelAndView list() throws Exception {
	 * List<BoardVO> list = boardService.listAll(); // ModelAndView - �𵨰� ��
	 * ModelAndView mav = new ModelAndView(); mav.setViewName("list"); // �並
	 * list.jsp�� ���� mav.addObject("list", list); // �����͸� ���� return mav; // list.jsp��
	 * List�� ���޵ȴ�. }
	 */
	@RequestMapping(value="/list")
	public String list(Model model) throws Exception {
		log.info(11+"");
		List<BoardVO> list = (List<BoardVO>)boardService.listAll();
		model.addAttribute("list", list);
		return "list"; // list.jsp�� List�� ���޵ȴ�.
	}

	// 02_01. �Խñ� �ۼ�ȭ��
	// @RequestMapping("board/write.do")
	// value="", method="���۹��"
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "write"; // write.jsp�� �̵�
	}

	// 02_02. �Խñ� �ۼ�ó��
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo) throws Exception {
		boardService.create(vo);
		return "redirect:list";
	}

	// 03. �Խñ� �󼼳��� ��ȸ, �Խñ� ��ȸ�� ���� ó��
	// @RequestParam : get/post ������� ���޵� ���� 1��
	// HttpSession ���ǰ�ü
	@RequestMapping(value = "/view")
	public ModelAndView view(int bno) throws Exception {
		log.info(bno+"");
		
		boardService.increaseViewcnt(bno); 
		// ��(������)+��(ȭ��)�� �Բ� �����ϴ� ��ü
		ModelAndView mav = new ModelAndView();
		// ���� �̸�
		mav.setViewName("/view");
		// �信 ������ ������
		mav.addObject("dto", boardService.read(bno));
		return mav;
	}

	// 04. �Խñ� ����
	// ������ �Է��� ������� @ModelAttribute BoardVO vo�� ���޵�
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		boardService.update(vo);
		return "redirect:list";
	}

	// 05. �Խñ� ����
	@RequestMapping(value="/delete", method= RequestMethod.POST)
	public String delete(@ModelAttribute BoardVO vo) throws Exception {
		boardService.delete(vo.getBno());
		return "list";
	}

}
