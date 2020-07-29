package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.happyhouse.model.dto.Notice;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDao noticeDao;
	
	public NoticeServiceImpl() {
		noticeDao = new NoticeDaoImpl();
	}

	@Override
	public void writeArticle(Notice noticeDto) throws Exception {
		if(noticeDto.getSubject() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		noticeDao.writeArticle(noticeDto);
	}

	@Override
	public List<Notice> listArticle(String key, String word) throws Exception {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		return noticeDao.listArticle(key, word);
	}

	@Override
	public Notice getArticle(int articleno) throws Exception {
		return noticeDao.getArticle(articleno);
	}

	@Override
	public void modifyArticle(Notice noticeDto) throws Exception {
		noticeDao.modifyArticle(noticeDto);
	}

	@Override
	public void deleteArticle(int articleno) throws Exception {
		noticeDao.deleteArticle(articleno);
	}

}
