package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.ReplyDao;
import com.bit2015.mysite.vo.ReplyVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class ReplyAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
		String spost_no = request.getParameter("no");
		long post_no=Long.parseLong(spost_no);
		
		String smember_no=request.getParameter("rememno");
		long member_no = Long.parseLong(smember_no);
		
		String content=request.getParameter("recontent");
		String member_name=request.getParameter("rename");
		ReplyVo vo = new ReplyVo();
		vo.setContent(content);
		vo.setMember_name(member_name);
		vo.setMember_no(member_no);
		vo.setPost_no(post_no);
		
		ReplyDao dao = ReplyDao.getInstance();
		
		dao.insert(vo);
		WebUtil.redirect(request, response, "board?a=view&no="+post_no);
		} catch (SQLException e) {
			System.out.println("db error : "+e );
		}
	
	}

}
