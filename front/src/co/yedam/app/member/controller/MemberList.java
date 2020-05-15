package co.yedam.app.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.app.common.Controller;
import co.yedam.app.member.model.MemberService;
import co.yedam.app.member.model.MemberVO;

public class MemberList implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.파라미터 생략
		//2.서비스 로직 처리하고 결과저장  (같이하기)
		ArrayList<MemberVO> list = MemberService.getInstance().getMemberList();
		request.setAttribute("list",list);
		
		return "/member/memberList.jsp";
	}

}
