package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Member;

@Mapper
public interface MemberMappers {
	public boolean insert(Member member);
	public boolean delete(Member member);
	public Member select(Member member);
	public void update(Member member);
	public String getPassword(Member member);
	public List<Member> selectAll();
	public int idchk(String id);
}
