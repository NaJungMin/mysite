package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.dao.ReplyDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.mysite.vo.ReplyVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class ViewAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
try {
			
			String sno=request.getParameter("no");
			long no = Long.parseLong(sno);
			BoardDao dao = BoardDao.getInstance();
			BoardVo vo = dao.getView(no);
			dao.plusCnt(no);
			request.setAttribute( "vo", vo );
			
			ReplyDao rdao = ReplyDao.getInstance();
			List<ReplyVo> rvo = rdao.getList(no);
			request.setAttribute("rvo", rvo);
			
			request.setAttribute("post_no", no);
			
			WebUtil.forward(request, response, "/views/board/view.jsp" );
			
		} catch( SQLException ex ) {
			System.out.println( "DBError : " + ex );
		}
	}

}
