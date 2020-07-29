package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;

@WebServlet("/admin.do")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		// TODO : act = user 처리 정리 
		//유저페이지로 이동 (로그인 상태 아닐시 로그인페이지, 로그인 상태일시 회원정보조회 페이지)
		if(act.equals("userList")) {
			userList(request, response);
		}
	}

	private void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/page/user/user.jsp";
		MemberService memberService = new MemberServiceImpl();
		//상품정보들 챙겨서 상품리스트페이지로 포워드
		List<Member> list = null;
		try {
			list = memberService.getMemberList();
		}
		catch(Exception e) {
			//예외페이지로
		}

		for(Member member: list) {
			System.out.println(member.toString());
		}
		request.setAttribute("list", list);
		path = "/WEB-INF/page/admin/userList.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);

	}
}
