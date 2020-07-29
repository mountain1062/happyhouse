package com.ssafy.happyhouse.model.dao;

import java.util.ArrayList;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDTO;

public interface SelectBoxDAO {

	ArrayList<SidoCodeDTO> selectSido() throws Exception;

	ArrayList<SidoCodeDTO> selectGugun(String sido) throws Exception;

	ArrayList<HouseInfo> selectDong(String gugun) throws Exception;

	ArrayList<HouseInfo> selectApt(String dong) throws Exception;

}
