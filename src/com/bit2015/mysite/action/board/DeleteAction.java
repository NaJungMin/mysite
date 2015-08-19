package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class DeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		UserVo uvo = (UserVo)session.getAttribute( "authUser" );
		
		
		String smember_no = request.getParameter("member_no");
		int member_no = Integer.parseInt(smember_no);
		
		if(uvo==null||uvo.equals("")||uvo.getNo()!=member_no){
			//로그인실패
			WebUtil.forward(request, response, "/views/board/deleteform.jsp");
			return;
			//리다이렉트를 했다고 메서드가 끝나지않음. 반드시 return 구문으로 메서드 종료를 해줘야함.
		}else{
			
			try{
				System.out.println("연산구문은 넘어왔습니다");
				String sno = request.getParameter("no");
				
				BoardDao dao = BoardDao.getInstance();
				dao.delete(sno);
				System.out.println("다오성공");
				WebUtil.redirect(request, response, "board");
				
				}catch(SQLException ex){
					System.out.println("db error :"+ex);
				}
				
			
		}
		
		
	
	}

}
