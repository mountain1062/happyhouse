package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.util.DBUtil;

public class NoticeDaoImpl implements NoticeDao {

	@Override
	public void writeArticle(Notice noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("insert into notice (userid, subject, content, regtime) \n");
			insertMember.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setInt(1, noticeDto.getUserid());
			pstmt.setString(2, noticeDto.getSubject());
			pstmt.setString(3, noticeDto.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public List<Notice> listArticle(String key, String word) throws SQLException {
		List<Notice> list = new ArrayList<Notice>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select articleno, userid, name as username, subject, content, regtime \n");
			sql.append("from notice a join member b on a.userid=b.no \n");
			if(!word.isEmpty()) {
				if("subject".equals(key)) {
					sql.append("where subject like ? \n");
				} else {
					sql.append("where " + key + " = ? \n");
				}
			}
			sql.append("order by articleno desc \n");
			pstmt = conn.prepareStatement(sql.toString());
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(1, "%" + word + "%");
				else
					pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setArticleno(rs.getInt("articleno"));
				notice.setUserid(rs.getInt("userid"));
				notice.setUsername(rs.getString("username"));
				notice.setSubject(rs.getString("subject"));
				notice.setContent(rs.getString("content"));
				notice.setRegtime(rs.getString("regtime"));
				
				list.add(notice);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}

	@Override
	public Notice getArticle(int articleno) throws SQLException {
		Notice notice = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select articleno, userid, name as username, subject, content, regtime \n");
			sql.append("from notice a join member b on a.userid=b.no \n");
			sql.append("where articleno = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notice = new Notice();
				notice.setArticleno(rs.getInt("articleno"));
				notice.setUserid(rs.getInt("userid"));
				notice.setUsername(rs.getString("username"));
				notice.setSubject(rs.getString("subject"));
				notice.setContent(rs.getString("content"));
				notice.setRegtime(rs.getString("regtime"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return notice;
	}

	@Override
	public void modifyArticle(Notice noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("update notice \n");
			insertMember.append("set subject = ?, content = ? \n");
			insertMember.append("where articleno = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, noticeDto.getSubject());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setInt(3, noticeDto.getArticleno());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public void deleteArticle(int articleno) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from notice \n");
			sql.append("where articleno = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleno);
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

}
