package com.bit2015.mysite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.vo.UserVo;

import net.sf.json.JSONObject;

@WebServlet("/json")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		/*
		 * var o{
		 * 	"name": "안대혁",
		 * 	"message": "안녕하세요"
		 * }
		 */
		
		List<UserVo> list = new ArrayList<UserVo>();
		UserVo vo = new UserVo();
		vo.setName("안대혁");
		vo.setEmail("nono@gmail.com");
		list.add(vo);
		list.add(new UserVo());
		list.add(new UserVo());
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "안대혁");
		map.put("message", "안녕하세요");
		map.put("list", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		out.println(jsonObject.toString());
		
		
		
	}

}
