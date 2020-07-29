package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;

import com.ssafy.happyhouse.model.dao.SelectBoxDAO;
import com.ssafy.happyhouse.model.dao.SelectBoxDAOImpl;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDTO;

public class SelectBoxServiceImpl implements SelectBoxService {

	private SelectBoxDAO dao;

	public SelectBoxServiceImpl() {
		dao = new SelectBoxDAOImpl();
	}

	@Override
	public ArrayList<SidoCodeDTO> selectSido() throws Exception {
		ArrayList<SidoCodeDTO> list = dao.selectSido();
		return list;
	}

	@Override
	public ArrayList<SidoCodeDTO> selectGugun(String sido) throws Exception {
		ArrayList<SidoCodeDTO> list = dao.selectGugun(sido);
		return list;
	}

	@Override
	public ArrayList<HouseInfo> selectDong(String gugun) throws Exception {
		ArrayList<HouseInfo> list = dao.selectDong(gugun);
		return list;
	}

	@Override
	public ArrayList<HouseInfo> selectApt(String dong) throws Exception {
		ArrayList<HouseInfo> list = dao.selectApt(dong);
		return list;
	}

}
