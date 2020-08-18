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

@Controller //현재 클래스를 스프링에서 관리하는 컨트롤러 bean 으로 생성
public class MemberController {
	//로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberServiceImp1 memberService;
	
	// 01. 로그인 화면
	@RequestMapping(value="login")
	public String login() {
		return "login";
	}
	
	//02. 로그인 처리
	@RequestMapping(value="loginCheck", method = RequestMethod.POST)
	public String list(Model model,MemberVO vo, HttpSession session) throws Exception{
		boolean result = memberService.loginCheck(vo, session);
		if(result == true) { // 로그인 성공
			//main.jsp로 이동
			/*
			 * model.addAttribute("home");
			 */model.addAttribute("msg","success");
		}else { //로그인 실패
			/*
			 * //login.jsp 로 이동 model.addAttribute("login");
			 */
			model.addAttribute("msg", "failure");
		}
		return "login";
	}
	
	// 03. 로그아웃 처리
	@RequestMapping(value="logout")
	public String logout(Model model, HttpSession session) throws Exception{
		memberService.logout(session);
		model.addAttribute("msg","logout");
		return "login";
	}

}
