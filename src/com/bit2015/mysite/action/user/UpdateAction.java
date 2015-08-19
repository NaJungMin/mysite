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

public class UpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			String repassword=request.getParameter("repassword");

			UserDao dao = UserDao.getInstance();
			UserVo vo = dao.get(email, password);
			
			if (vo == null) {
				// 로그인실패
				WebUtil.redirect(request, response, "user?a=updateform&error=true");
				return;
				// 리다이렉트를 했다고 메서드가 끝나지않음. 반드시 return 구문으로 메서드 종료를 해줘야함.
			}
			
			
			// login 성공한 경우
			
			if(repassword==null||repassword.equals("")){
				vo.setName(name);
				vo.setPassword(password);
				vo.setGender(gender);
			}else{
				vo.setName(name);
				vo.setPassword(repassword);
				vo.setGender(gender);
			}
			
			
			// update 처리
			dao.update(vo, email);
			
			//세션수정된버전으로업데이트
			HttpSession session = request.getSession();
			//session.setAttribute("authUser", vo);
			UserVo vo1 = (UserVo)session.getAttribute( "authUser" );
			vo1.setName(name);

			// main 리다이렉션
			WebUtil.redirect(request, response, "/user?a=updatesuccess");
		} catch (SQLException e) {
			System.out.println("db오류 : " + e);
		}
	}
}
