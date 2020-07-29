package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeService {

	public void writeArticle(Notice noticeDto) throws Exception;
	public List<Notice> listArticle(String key, String word) throws Exception;
	
	public Notice getArticle(int articleno) throws Exception;
	public void modifyArticle(Notice noticeDto) throws Exception;
	public void deleteArticle(int articleno) throws Exception;
	
}
