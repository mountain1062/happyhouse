package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDTO;
import com.ssafy.happyhouse.util.DBUtil;

public class SelectBoxDAOImpl implements SelectBoxDAO {

	@Override
	public ArrayList<SidoCodeDTO> selectSido() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SidoCodeDTO> list = new ArrayList<SidoCodeDTO>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(sido_code,2) sido_code, sido_name FROM sidocode \n");
			sql.append("ORDER BY sido_code");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoCodeDTO dto = new SidoCodeDTO();
				dto.setSido_code(rs.getString("sido_code"));
				dto.setSido_name(rs.getString("sido_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public ArrayList<SidoCodeDTO> selectGugun(String sido) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SidoCodeDTO> list = new ArrayList<SidoCodeDTO>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(gugun_code,5) gugun_code, gugun_name FROM guguncode \n");
			sql.append("where left(gugun_code,2) = ?");
			sql.append("ORDER BY gugun_code");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoCodeDTO dto = new SidoCodeDTO();
				dto.setGugun_code(rs.getString("gugun_code"));
				dto.setGugun_name(rs.getString("gugun_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public ArrayList<HouseInfo> selectDong(String gugun) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HouseInfo> list = new ArrayList<HouseInfo>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct dong, code FROM houseinfo \n");
			sql.append("where code = ? \n");
			sql.append("ORDER BY dong");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseInfo dto = new HouseInfo();
				dto.setCode(Integer.parseInt(rs.getString("code")));
				dto.setDong(rs.getString("dong"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public ArrayList<HouseInfo> selectApt(String dong) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HouseInfo> list = new ArrayList<HouseInfo>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT no,dong,AptName,code,jibun,lat,lng FROM ssafydb.houseinfo WHERE dong = ? \n");
			sql.append("ORDER BY AptName");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseInfo dto = new HouseInfo();
				dto.setNo(Integer.parseInt(rs.getString("no")));
				dto.setDong(rs.getString("dong"));
				dto.setAptName(rs.getString("AptName"));
				dto.setCode(Integer.parseInt(rs.getString("code")));
				dto.setJibun(rs.getString("jibun"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

}
