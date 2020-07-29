package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.service.NoticeService;

@Controller("/notice")
public class NoticeController {
	private NoticeService noticeService;
	
	public void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/WEB-INF/page/site/notice.jsp";
		String result = "";
		String articleno = request.getParameter("articleno");
		if(articleno != null && !articleno.isEmpty()) {
			noticeService.deleteArticle(Integer.parseInt(articleno));
			result = "삭제되었습니다.";
		}else {
			result = "삭제할 글 정보가 없습니다.";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void gotoModifyNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/WEB-INF/page/site/noticewrite.jsp";
		int articleno = Integer.parseInt(request.getParameter("articleno"));
		Notice notice = noticeService.getArticle(articleno);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void showNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/WEB-INF/page/site/notice.jsp";
		int articleno = Integer.parseInt(request.getParameter("no"));
		Notice notice = noticeService.getArticle(articleno);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void showNoticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "/WEB-INF/page/site/noticelist.jsp";
		List<Notice> notices = noticeService.listArticle("", "");
		request.setAttribute("notices", notices);
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	protected void gotoNoticeWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/page/site/noticewrite.jsp").forward(request, response);
	}
	
	protected void saveNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("userinfo");
		if(member != null) {
			String articleno = request.getParameter("articleno");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			if(articleno == null || articleno.isEmpty()) {
				noticeService.writeArticle(new Notice(member.getNo(), subject, content));
			}else {
				Notice notice = new Notice(member.getNo(), subject, content);
				notice.setArticleno(Integer.parseInt(articleno));
				noticeService.modifyArticle(notice);
			}
			request.setAttribute("result", "저장이 완료되었습니다.");
		}else {
			throw new Exception("잘못된 접근입니다.");
		}
		request.getRequestDispatcher("/WEB-INF/page/site/noticewrite.jsp").forward(request, response);
	}
}
