package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberDao {
	public boolean insert(Member member) throws SQLException;
	public boolean delete(String id, String password) throws SQLException;
	public Member select(String id, String password) throws SQLException;
	public Member select(int no) throws SQLException;
	public void update(int no, String password, String name, String address, String phone) throws SQLException;
	public String getPassword(String id, String phone)throws SQLException;
	public List<Member> selectAll() throws SQLException;
	public int idchk(String id) throws SQLException;
}
