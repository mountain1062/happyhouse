package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberService {
	public boolean signUp(String id, String password, String name, String address, String phone);
	public boolean withdrawal(String id, String password);
	public Member login(String id, String password) throws Exception;
	public Member update(int no, String password, String name, String address, String phone) throws Exception;
	public void logout();
	public Member getMemberInfo();
	public String findPassword(String id, String phone);
	public List<Member> getMemberList() throws SQLException;
	public int idchk(String id) throws SQLException;
}
