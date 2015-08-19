package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class UpdateFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
			String sno=request.getParameter("no");
			long no = Long.parseLong(sno);
			BoardDao dao = BoardDao.getInstance();
			
			BoardVo vo = dao.getView(no);
			request.setAttribute( "vo", vo );
			
			WebUtil.forward(request, response, "/views/board/updateform.jsp" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
					
		WebUtil.forward(request, response, "/views/board/updateform.jsp");
	
	}

}
