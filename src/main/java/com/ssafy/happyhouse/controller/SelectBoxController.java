package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDTO;
import com.ssafy.happyhouse.model.service.SelectBoxService;
import com.ssafy.happyhouse.model.service.SelectBoxServiceImpl;

@WebServlet("/SelectBoxController")
public class SelectBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SelectBoxService service;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new SelectBoxServiceImpl();
	}

	public SelectBoxController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		if(command.equals("sido")) {
			PrintWriter out = response.getWriter();
			ArrayList<SidoCodeDTO> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectSido();
				for(SidoCodeDTO dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("sido_code", dto.getSido_code());
					obj.put("sido_name", dto.getSido_name());
					arr.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//sido
		else if(command.equals("gugun")) {
			String sido = request.getParameter("sido");
			PrintWriter out = response.getWriter();
			ArrayList<SidoCodeDTO> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectGugun(sido);
				for(SidoCodeDTO dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("gugun_code", dto.getGugun_code());
					obj.put("gugun_name", dto.getGugun_name());
					arr.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//gugun
		else if(command.equals("dong")) {
			String gugun = request.getParameter("gugun");
			PrintWriter out = response.getWriter();
			ArrayList<HouseInfo> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectDong(gugun);
				for(HouseInfo dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("code", dto.getDong());
					obj.put("dong", dto.getDong());
					arr.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//dong
		else if(command.equals("apt")) {
			String dong = request.getParameter("dong");
			PrintWriter out = response.getWriter();
			ArrayList<HouseInfo> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = service.selectApt(dong);
				for(HouseInfo dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("no", dto.getNo());
					obj.put("dong", dto.getDong());
					obj.put("AptName", dto.getAptName());
					obj.put("code", dto.getCode());
					obj.put("jibun", dto.getJibun());
					arr.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//dong
	}//process

}
