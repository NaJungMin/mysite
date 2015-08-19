package com.bit2015.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.vo.GuestBookVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class IndexAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {
			
			GuestBookDao dao = GuestBookDao.getInstance();
			List<GuestBookVo> list = dao.getList();
			
			request.setAttribute( "list", list );
			WebUtil.forward(request, response, "/views/guestbook/list.jsp" );
			
		} catch( SQLException ex ) {
			System.out.println( "DBError : " + ex );
		}
	}

}
