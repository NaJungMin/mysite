package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class SearchAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
		String kwd=request.getParameter("kwd");
		BoardDao dao = BoardDao.getInstance();
		List<BoardVo> list = dao.search(kwd);
		int stnum=list.size();
		request.setAttribute( "list", list );
		request.setAttribute( "stnum", stnum );
		WebUtil.forward(request, response, "/views/board/list.jsp");
		
		
		} catch (SQLException e) {
			//System.out.println("DB error : "+e);
			e.printStackTrace();
		}
		
	
	}

}
