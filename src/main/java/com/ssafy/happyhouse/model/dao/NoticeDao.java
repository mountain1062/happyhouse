package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeDao {

	public void writeArticle(Notice noticeDto) throws SQLException;
	public List<Notice> listArticle(String key, String word) throws SQLException;
	
	public Notice getArticle(int articleno) throws SQLException;
	public void modifyArticle(Notice noticeDto) throws SQLException;
	public void deleteArticle(int articleno) throws SQLException;
	
}
