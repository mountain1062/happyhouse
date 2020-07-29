package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/user.do")
public class UserController extends HttpServlet {
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
		String root = request.getContextPath();
		String path = "/WEB-INF/page/user/user.jsp";
		String act = request.getParameter("act");
		
		// TODO : act = user 처리 정리 
		//유저페이지로 이동 (로그인 상태 아닐시 로그인페이지, 로그인 상태일시 회원정보조회 페이지)
		if(act == null || act.equals("user")) {
			request.getRequestDispatcher(root).forward(request, response);
		}
		// 로그인
		else if(act.equals("login")) {
			login(request, response);
		}
		// 로그아웃 
		else if(act.equals("logout")) {
			logout(request, response);
		}
		// 회원가입
		else if(act.equals("mvSingUp")) {
			path = "/WEB-INF/page/user/signUp.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
		else if(act.equals("signUp")) {
			signUp(request, response);
		}
		// 회원탈퇴
		else if(act.equals("mvWithdrawal")) {
			path = "/WEB-INF/page/user/withdrawal.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
		else if(act.equals("withdrawal")) {
			withdrawal(request, response);
		}
		// 회원정보조회 
		else if(act.equals("confirm")) {
//			response.sendRedirect(root + "/user.do?act=user");
			request.getRequestDispatcher(path).forward(request, response);
		}
		// 회원정보수정
		else if(act.equals("update")) {
			update(request, response);
		}
		// 비밀번호찾기
		else if(act.equals("mvFindPassword")) {
			path = "/WEB-INF/page/user/findPassword.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
		else if(act.equals("findPassword")) {
			findPassword(request, response);
		}else if(act.equals("idchk")) {
			IDchk(request, response);
		}
		// 회원정보조회 (admin) 
		
	}
	
	private void IDchk(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberService memberService = new MemberServiceImpl();
		String id = request.getParameter("inputID");
		int idCnt = 0;
		try {			
			idCnt = memberService.idchk(id);
		}catch(Exception e) {
			idCnt = -1;
			e.printStackTrace();
		}finally {
			PrintWriter out = response.getWriter();
			out.print(idCnt);
			out.close();
		}
	}

	private void withdrawal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = "/WEB-INF/page/user/user.jsp";
		
		MemberService memberService = new MemberServiceImpl();
		String root = request.getContextPath();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		boolean result = memberService.withdrawal(id, password);
		System.out.println("controller :"+result);
		if(!result) {
			path = "/WEB-INF/page/user/withdrawal.jsp";
			request.setAttribute("error", "비밀번호를 확인해주세요 ");
		}
		else if(result){
			
			HttpSession session = request.getSession();
			session.removeAttribute("userinfo");
			
			Cookie cookies[] = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if("cookie_id".equals(cookie.getName())) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						break;
					}
				}
			}
			
			path = "index.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	// TODO : 회원가입시 중복 체크 (serviceImpl에서 에러 출력은 하는데...)
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberService memberService = new MemberServiceImpl();
		String root = request.getContextPath();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		boolean res = false;
		try {
			res = memberService.signUp(id, password, name, address, phone);	
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(root + "/user.do");
	}

	private void findPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberService = new MemberServiceImpl();
		String path = "/WEB-INF/page/user/findPassword.jsp";
		
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		String password = memberService.findPassword(id, phone);
		
		if(password == null) {
			request.setAttribute("error", "아이디, 휴대폰 번호를 확인해주세요");
		}
		else {
			request.setAttribute("msg", password);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	// update시 password도 함께 update됨 
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		MemberService memberService = new MemberServiceImpl();
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("userinfo");
		
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		try {
			member = memberService.update(member.getNo(), password, name, address, phone);
			session.setAttribute("userinfo", member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(root + "/user.do?act=user");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = "index.jsp";
		String userid = request.getParameter("mbr_id");
		String userpwd = request.getParameter("mbr_pwd");
		Member member = null;
		String json = "";
		System.out.println(userid);
		try {
			MemberService memberService = new MemberServiceImpl();
			member = memberService.login(userid, userpwd);
			if(member != null) {
				// session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", member);
				// cookie 설정
				String idsave = request.getParameter("saveCheck");
				if("saveok".equals(idsave)) {//아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("cookie_id", userid);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
					response.addCookie(cookie);
				} 
				//아이디 저장을 해제 했다면 cookie 삭제 
				else {
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("cookie_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
			} else {
				path = "/WEB-INF/page/user/user.jsp";
				request.setAttribute("error", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}finally {
			PrintWriter out = response.getWriter();
			out.print(json);
			out.close();
		}
		
		//request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		
		HttpSession session = request.getSession();
		session.removeAttribute("userinfo");
		session.removeAttribute("admin");
		
		response.sendRedirect(root);
	}
}
