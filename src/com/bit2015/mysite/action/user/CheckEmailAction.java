package com.bit2015.mysite.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.UserDao;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;

import net.sf.json.JSONObject;

public class CheckEmailAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String email = request.getParameter("email");

			UserDao dao = UserDao.getInstance();

			UserVo vo = dao.get(email);

			// {"result":"exist"}
			// {"result":"not exist"}
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", (vo == null) ? "not exist" : "exist");
			JSONObject jsonObject = JSONObject.fromObject(map);
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonObject.toString());

		} catch (SQLException e) {
			System.out.println("db 오류 : " + e);
		}

	}

}
