package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class UpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			String sno=request.getParameter("no");
			long no = Long.parseLong(sno);
			BoardDao dao = BoardDao.getInstance();
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
		
			dao.update(no, title, content);
			
			
			WebUtil.redirect(request, response, "board?a=view&no="+sno);
			
		} catch (SQLException e) {
			System.out.println("dberror : " + e);
		}

	}

}
