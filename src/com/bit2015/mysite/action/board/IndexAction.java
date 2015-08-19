package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.dao.DapDao;
import com.bit2015.mysite.dao.ReplyDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class IndexAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
try {
			
			BoardDao dao = BoardDao.getInstance();
			ReplyDao rdao = ReplyDao.getInstance();
			int num=0;
			int panum=0;
			int total = dao.totalPage();
			//int retotal=rdao.totalPage();
			int to =0;
			if(total%5==0){
				to=total/5;
			}else{
				to=(total/5)+1;
			}
			List<BoardVo> list = null;
			try {
				String snum=request.getParameter("num");
				num=Integer.parseInt(snum);
				if (num <= 0 || num > to) {
					num=1;
					panum=1;
					list = dao.getList(num);
				}else{
					list = dao.getList(num);
					if (num <= 5) {
						panum = 1;
					} else if (num > 5 && num < 11) {
						panum = 6;
					} else {
						int tom = num / 5;
						panum = 5 * tom + 1;
					}
				}
				
			} catch (Exception e) {
					num=1;
					panum=1;
					list = dao.getList(num);
			}
			
			int stnum=total-(num-1)*5;
			request.setAttribute( "list", list );
			request.setAttribute("total", total);
			request.setAttribute("panum", panum);
			//request.setAttribute("retotal", retotal);
			request.setAttribute("num", num);
			request.setAttribute("to", to);
			request.setAttribute("stnum", stnum);
			WebUtil.forward(request, response, "/views/board/list.jsp" );
			
		} catch( SQLException ex ) {
			System.out.println( "DBError : " + ex );
		}
	
	}

}
