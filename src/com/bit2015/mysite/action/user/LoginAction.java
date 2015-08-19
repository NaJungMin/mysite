package com.bit2015.mysite.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.UserDao;
import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class LoginAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub

		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserDao dao = UserDao.getInstance();
			UserVo vo = dao.get(email, password);
			
			if(vo==null){
				//로그인실패
				WebUtil.redirect(request, response, "user?a=loginform&error=true");
				return;
				//리다이렉트를 했다고 메서드가 끝나지않음. 반드시 return 구문으로 메서드 종료를 해줘야함.
			}
			
			//login 성공한 경우
			//session 처리
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", vo);
			
			//main 리다이렉션
			WebUtil.redirect(request, response, "/");
			
		} catch (SQLException e) {
			System.out.println("db오류 : " + e);
		}

	}

}
