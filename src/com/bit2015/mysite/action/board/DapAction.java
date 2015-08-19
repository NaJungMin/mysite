package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.dao.ReplyDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.mysite.vo.ReplyVo;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class DapAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
			HttpSession session = request.getSession();
			UserVo uvo = (UserVo)session.getAttribute( "authUser" );
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String smy_no = request.getParameter("my_no");
			Long member_no=uvo.getNo();
			String member_name=uvo.getName();
			String sdap = request.getParameter("dap");
			long dap = Long.parseLong(sdap);
			long my_no = Long.parseLong(smy_no);
			String sorder_no = request.getParameter("order_no");
			long order_no=Long.parseLong(sorder_no);
			
			System.out.println(order_no+" : "+my_no+" : "+dap);
				
				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setMember_no(member_no);
				vo.setMember_name(member_name);
				vo.setDap(dap+1);
				vo.setMy_no(my_no);
				
			
				BoardDao dao = BoardDao.getInstance();
			if(order_no>0){
				vo.setOrder_no(order_no+1);
				
			}else{
				vo.setOrder_no(1);
				
			}
			System.out.println("voset완료");
			dao.dapUpdate(my_no, order_no);
				System.out.println("이전글 업데이트");
				dao.dapInsert(vo);
				
			
		WebUtil.redirect(request, response, "board");
		} catch (SQLException e) {
			System.out.println("db error : "+e );
		}
	
	}

}
