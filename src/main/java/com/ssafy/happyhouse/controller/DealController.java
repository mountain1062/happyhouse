package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.service.HouseService;

@Controller("deal")
public class DealController {
	@Autowired
	private HouseService houseService;

	/**
	 *	메인화면
	 *	지역검색 & 지도초기화면
	 */
	@GetMapping("/main")
	public String getEmptyList() {
		System.out.println("dd");
		return "deal/main";
	}
	
	/** 거래 내역 상세 */
	public void getDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		if(no != null || !no.isEmpty()) {
			HouseDeal deal = houseService.search(Integer.parseInt(no));
			request.setAttribute("deal", deal);
			request.getRequestDispatcher("/WEB-INF/page/deal/detail.jsp").forward(request, response);
		}
	}
	
	/** 거래 내역 조회 */
	//TODO 페이징처리
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] chksearchtype = request.getParameterValues("chksearchtype");
		boolean[] searchType = new boolean[4];
		if(chksearchtype != null) {
			for(int i=0; i<chksearchtype.length; i++) {
				searchType[Integer.parseInt(chksearchtype[i])] = true;
			}
		}else {
			for(int i=0; i<4; i++) {
				searchType[i] = true;
			}
		}
		String dealType = request.getParameter("dealtype");
		dealType = dealType == null ? "all" : dealType;
//		String dealKeyword = request.getParameter("dealkeyword");
//		dealKeyword = dealKeyword == null ? "" : dealKeyword;
		HousePageBean bean = new HousePageBean();
		bean.setSearchType(searchType);
		switch(dealType) {
		case "dong":
			String sicode = request.getParameter("sido");
			String gucode = request.getParameter("gugun");
			String dongcode = request.getParameter("dong");
			bean.setDong(dongcode);
			request.setAttribute("sido", sicode);
			request.setAttribute("gugun", gucode);
			request.setAttribute("dong", dongcode);
			break;
//		case "aptname":bean.setAptname(dealKeyword); break;
		}
		List<HouseDeal> deals = houseService.searchAll(bean);
		request.setAttribute("deals", deals);
		request.setAttribute("searchtype", searchType);
		request.setAttribute("dealtype", dealType);
		request.getRequestDispatcher("/WEB-INF/page/deal/list.jsp").forward(request, response);
	}

	/** 거래 내역 조회 Json */
	//TODO 페이징처리
	public void searchjson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String[] chksearchtype = request.getParameterValues("chksearchtype");
		boolean[] searchType = new boolean[4];
		if(chksearchtype != null) {
			for(int i=0; i<chksearchtype.length; i++) {
				searchType[Integer.parseInt(chksearchtype[i])] = true;
			}
		}else {
			for(int i=0; i<4; i++) {
				searchType[i] = true;
			}
		}
		String dealType = request.getParameter("dealtype");
		dealType = dealType == null ? "all" : dealType;
//		String dealKeyword = request.getParameter("dealkeyword");
//		dealKeyword = dealKeyword == null ? "" : dealKeyword;
		HousePageBean bean = new HousePageBean();
		bean.setSearchType(searchType);
		switch(dealType) {
		case "dong":
			String sicode = request.getParameter("sido");
			String gucode = request.getParameter("gugun");
			String dongcode = request.getParameter("dong");
			bean.setDong(dongcode);
			request.setAttribute("sido", sicode);
			request.setAttribute("gugun", gucode);
			request.setAttribute("dong", dongcode);
			break;
//		case "aptname":bean.setAptname(dealKeyword); break;
		}
		PrintWriter out = response.getWriter();
		List<HouseDeal> list = null;
		JSONArray arr = new JSONArray();
		try {
			list = houseService.searchAll(bean);
			for(HouseDeal dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("no", dto.getNo());
				obj.put("dong", dto.getDong());
				obj.put("AptName", dto.getAptName());
				obj.put("code", dto.getCode());
				obj.put("jibun", dto.getJibun());
				obj.put("lat", dto.getLat());
				obj.put("lng", dto.getLng());
				arr.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
//		request.setAttribute("deals", deals);
//		request.setAttribute("searchtype", searchType);
//		request.setAttribute("dealtype", dealType);
	}
}
