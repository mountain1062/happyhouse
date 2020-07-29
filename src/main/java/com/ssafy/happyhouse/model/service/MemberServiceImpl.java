package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dao.MemberDaoImpl;
import com.ssafy.happyhouse.model.dto.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao;
	private Member member;
	
	public MemberServiceImpl() {
		memberDao = new MemberDaoImpl();
	}

	@Override
	public Member getMemberInfo() {
		return member;
	}

	@Override
	public boolean signUp(String id, String password, String name, String address, String phone) {
		if (id.trim().isEmpty()) {
			throw new HappyHouseException("아이디를 입력하세요.");
		} else if (password.trim().isEmpty()) {
			throw new HappyHouseException("비밀번호를 입력하세요.");
		} else if (name.trim().isEmpty()) {
			throw new HappyHouseException("이름을 입력하세요.");
		} else if (address.trim().isEmpty()) {
			throw new HappyHouseException("주소를 입력하세요.");
		} else if (phone.trim().isEmpty()) {
			throw new HappyHouseException("연락처를 입력하세요.");
		} else {
			try {
				return memberDao.insert(new Member(id, password, name, address, phone));
			} catch (Exception e) {
				e.printStackTrace();
				throw new HappyHouseException("회원가입 중에 오류발생");
			}
		}
	}
	
	@Override
	public boolean withdrawal(String id, String password) {
		boolean result = false;
		if (id.trim().isEmpty()) {
			throw new HappyHouseException("아이디를 입력하세요.");
		} else if (password.trim().isEmpty()) {
			throw new HappyHouseException("비밀번호를 입력하세요.");
		} else {
			try {
				result = memberDao.delete(id, password);
			} catch (Exception e) {
				e.printStackTrace();
				throw new HappyHouseException("회원가입 중에 오류발생");
			}
		}
		System.out.println("service :"+result);
		return result;
	}

	@Override
	public Member login(String id, String password) throws Exception {
		if (id.trim().isEmpty()) {
			throw new HappyHouseException("아이디를 입력하세요.");
		} else if (password.trim().isEmpty()) {
			throw new HappyHouseException("비밀번호를 입력하세요.");
		} else {
			return memberDao.select(id, password);
		}
	}

	@Override
	public void logout() {
		this.member = null;
	}

	@Override
	public Member update(int no, String password, String name, String address, String phone) throws Exception {
		try {
			memberDao.update(no, password, name, address, phone);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HappyHouseException("회원 정보 수정 중에 오류발생");
		}
		
		return memberDao.select(no);
	}

	@Override
	public String findPassword(String id, String phone) {
		String password = null;
		try {
			password = memberDao.getPassword(id, phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return password;
	}

	@Override
	public List<Member> getMemberList() throws SQLException {
		return memberDao.selectAll();
	}

	@Override
	public int idchk(String id) throws SQLException {
		return memberDao.idchk(id);
	}
}
