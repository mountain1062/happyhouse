package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.util.DBUtil;

public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean insert(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean successCnt = false;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into member (id,password,name,address,phone) values(?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getAddress());
			stmt.setString(5, member.getPhone());
			successCnt=stmt.execute();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		return successCnt;
	}

	@Override
	public boolean delete(String id, String password) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean result = false;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "DELETE FROM member WHERE id = ? and password = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, password);
			count = stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		if (count == 1)
			result = true;
		System.out.println("DAO :" + result);
		return result;
	}

	@Override
	public Member select(String id, String password) throws SQLException {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT * FROM member WHERE id = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setNo(rs.getInt("no"));
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return member;
	}

	public Member select(int no) throws SQLException {
		Member member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT * FROM member WHERE no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setNo(rs.getInt("no"));
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return member;
	}

	@Override
	public void update(int no, String password, String name, String address, String phone) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = "UPDATE member SET password=?, name=?, address=?, phone=? WHERE no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);
			pstmt.setInt(5, no);
			pstmt.execute();

		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}

	@Override
	public String getPassword(String id, String phone) throws SQLException {
		String password = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT password FROM member WHERE id = ? and phone = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				password = rs.getString("password");
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return password;
	}

	@Override
	public List<Member> selectAll() throws SQLException {
		// 결과물을 담을 리스트를 준비한다.
		List<Member> list = new ArrayList<Member>();
		// 필요한 변수들을 미리 만들어둔다 (try catch구조가 블록이 나눠지니까 그거보다 하나 밖에서 변수를 준비)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 커넥션을 연결한다. 여기부터 try안에서
		try {
			conn = DBUtil.getConnection();
			// sql문을 준비한다.
			String sql = "SELECT * FROM member";
			// 준비한 sql문을 statement객체로 준비한다
			pstmt = conn.prepareStatement(sql);
			// pstmt의 인자매칭을 완성한다 여기선 없네...
			// 준비된 statement구문 객체를 던진다 !! 응답이 rs로 오면 받고
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("no"));
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				list.add(member);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public int idchk(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int Cnt = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT count(*) cnt FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Cnt = rs.getInt("cnt");
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return Cnt;
	}

}
