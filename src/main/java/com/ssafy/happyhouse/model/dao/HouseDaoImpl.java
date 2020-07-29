package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;

import ch.qos.logback.core.db.dialect.DBUtil;

@Repository
public class HouseDaoImpl implements HouseDao {
	private Map<String, HouseInfo> houseInfo;
	private Map<String, List<HouseDeal>> deals;
	private int size;
	private List<HouseDeal> search;
	private String[] searchType = { HouseDeal.APT_DEAL, HouseDeal.APT_RENT, HouseDeal.HOUSE_DEAL,
			HouseDeal.HOUSE_RENT };

	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를 검색해서 반환.
	 * 
	 * @param bean 검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean bean) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select a.*, b.img, lat, lng ");
			sql.append(", case type when 1 then '").append(HouseDeal.APT_DEAL);
			sql.append("' when 2 then '").append(HouseDeal.APT_RENT);
			sql.append("' when 3 then '").append(HouseDeal.HOUSE_DEAL);
			sql.append("' when 4 then '").append(HouseDeal.HOUSE_RENT);
			sql.append("' end as typestring");
			sql.append(" from housedeal a left outer join (select distinct aptName, trim(dong) as dong, img, lat, lng from houseinfo where lat is not null and lng is not null) b on (a.aptName = b.AptName and trim(a.dong) = b.dong) where 1=1 ");
			boolean[] type = bean.getSearchType();
			sql.append(" and type in (");
			for (int i = 0, size = type.length; i < size; i++) {
				if (type[i]) {
					sql.append(i + 1);
					sql.append(",");
				}
			}
			if (sql.lastIndexOf(",") == sql.length() - 1)
				sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			String dong = bean.getDong();
			String dongCode = bean.getDongcode();
			String aptName = bean.getAptname();
			if (dong != null && !dong.trim().equals("")) {
				sql.append(" and a.dong like ?");
			} else if (aptName != null && !aptName.trim().equals("")) {
				sql.append(" and a.aptname like ?");
			} else if (dongCode != null && !dongCode.trim().equals("")) {
				sql.append(" and code = ?");
			}
			stmt = con.prepareStatement(sql.toString());
			if (dong != null && !dong.trim().equals("")) {
				stmt.setString(1, "%" + dong + "%");
			} else if (aptName != null && !aptName.trim().equals("")) {
				stmt.setString(1, "%" + aptName + "%");
			} else if (dongCode != null && !dongCode.trim().equals("")) {
				stmt.setInt(1, Integer.parseInt(dongCode));
			}
			rs = stmt.executeQuery();
			List<HouseDeal> dealList = new LinkedList<HouseDeal>();
			while (rs.next()) {
				HouseDeal deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("AptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("typestring"));
				deal.setRentMoney(rs.getString("rentMoney"));
				deal.setLat(rs.getString("lat"));
				deal.setLng(rs.getString("lng"));
				deal.setImg(rs.getString("img"));
				dealList.add(deal);
			}
			return dealList;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

	/**
	 * 주택거래 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * 법정동+아파트명을 이용하여 HouseInfo에서 건축연도, 법정코드, 지번, 이미지 정보를 찾아서 HouseDeal에 setting한
	 * 정보를 반환한다.
	 * 
	 * @param no 검색할 아파트 식별 번호
	 * @return 아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select a.*, b.img from housedeal a left outer join (select distinct aptName, trim(dong) as dong, img from houseinfo) b on (a.aptName = b.AptName and trim(a.dong) = b.dong)  where a.no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			HouseDeal deal = new HouseDeal();
			if (rs.next()) {
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("AptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				deal.setImg(rs.getString("img"));
			}
			return deal;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

	public static void main(String[] args) {
		HouseDao dao = new HouseDaoImpl();
	}
}
