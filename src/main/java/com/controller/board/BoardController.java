package com.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.model.board.dto.BoardVO;
import com.service.board.BoardService;
import com.service.board.BoardServiceImp1;

@Controller // ����Ŭ������ ��Ʈ�ѷ� bean���� ���
public class BoardController {

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
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, HttpSession session) throws Exception {
		// ��ȸ�� ����ó��
		boardService.increaseViewcnt(bno, session);
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
		return "list";
	}

	// 05. �Խñ� ����
	@RequestMapping("delete")
	public String delete(@RequestParam int bno) throws Exception {
		boardService.delete(bno);
		return "list";
	}

}
