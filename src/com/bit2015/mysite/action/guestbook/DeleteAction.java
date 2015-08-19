package com.bit2015.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class DeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
		
		String sno = request.getParameter("no");
		String password = request.getParameter("password");
		GuestBookDao dao = GuestBookDao.getInstance();
		
		dao.delete(sno,password);
		
		WebUtil.redirect(request, response, "guestbook");
		
		}catch(SQLException ex){
			System.out.println("db error :"+ex);
		}
		
		
		
	
	}

}
