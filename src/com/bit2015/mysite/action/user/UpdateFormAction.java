package com.bit2015.mysite.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bit2015.mysite.dao.UserDao;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class UpdateFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		try {
		HttpSession session = request.getSession();
		//session.setAttribute("authUser", vo);
		UserVo vo1 = (UserVo)session.getAttribute( "authUser" );
		UserDao dao = UserDao.getInstance();
		UserVo vo = dao.get(vo1.getNo());
		
		request.setAttribute( "vo", vo );
		
		
		WebUtil.forward(request, response, "/views/user/updateform.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
