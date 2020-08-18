package com.controller.board;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.board.dto.MemberVO;
import com.service.board.MemberServiceImp1;

@Controller //���� Ŭ������ ���������� �����ϴ� ��Ʈ�ѷ� bean ���� ����
public class MemberController {
	//�α��� ���� ����
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberServiceImp1 memberService;
	
	// 01. �α��� ȭ��
	@RequestMapping(value="login")
	public String login() {
		return "login";
	}
	
	//02. �α��� ó��
	@RequestMapping(value="loginCheck", method = RequestMethod.POST)
	public String list(Model model,MemberVO vo, HttpSession session) throws Exception{
		boolean result = memberService.loginCheck(vo, session);
		if(result == true) { // �α��� ����
			//main.jsp�� �̵�
			/*
			 * model.addAttribute("home");
			 */model.addAttribute("msg","success");
		}else { //�α��� ����
			/*
			 * //login.jsp �� �̵� model.addAttribute("login");
			 */
			model.addAttribute("msg", "failure");
		}
		return "login";
	}
	
	// 03. �α׾ƿ� ó��
	@RequestMapping(value="logout")
	public String logout(Model model, HttpSession session) throws Exception{
		memberService.logout(session);
		model.addAttribute("msg","logout");
		return "login";
	}

}
