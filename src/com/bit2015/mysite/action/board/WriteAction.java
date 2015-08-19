package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class WriteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
		HttpSession session = request.getSession();
		UserVo uvo = (UserVo)session.getAttribute( "authUser" );
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long member_no=uvo.getNo();
		String member_name=uvo.getName();
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMember_no(member_no);
		vo.setMember_name(member_name);
		
		BoardDao dao = BoardDao.getInstance();
		dao.insert(vo);
		
		WebUtil.redirect(request, response, "board");
		} catch (SQLException e) {
			System.out.println("DB Error : "+e);
		}
		
		
		
	
	}

}
