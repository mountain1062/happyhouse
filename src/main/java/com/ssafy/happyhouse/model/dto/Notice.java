package com.ssafy.happyhouse.model.dto;

public class Notice {

	private int articleno;
	private int userid;
	private String username;
	private String subject;
	private String content;
	private String regtime;
	
	public Notice() {}
	public Notice(int userid, String subject, String content) {
		this.userid = userid;
		this.subject = subject;
		this.content = content;
	}
	public Notice(int articleno, int userid, String username, String subject, String content, String regtime) {
		this.articleno = articleno;
		this.userid = userid;
		this.username = username;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
