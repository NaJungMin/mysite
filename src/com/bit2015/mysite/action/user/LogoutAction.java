package com.bit2015.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class LogoutAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		if(session == null){
			WebUtil.redirect(request, response, "/");
			return;
		}
		
		//로그아웃처리
		session.removeAttribute("authUser");
		session.invalidate();
		
		WebUtil.redirect(request, response, "/");
	}
	
}
