package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.dao.ReplyDao;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class ReDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
			try{
				String sno = request.getParameter("no");
				String post_no=request.getParameter("post_no");
				System.out.println(post_no);
				
				ReplyDao dao = ReplyDao.getInstance();
				dao.delete(sno);
				
				WebUtil.redirect(request, response, "board?a=view&no="+post_no);
				
				}catch(SQLException ex){
					System.out.println("db error :"+ex);
				}
				
			
		}
		
		
	

}
