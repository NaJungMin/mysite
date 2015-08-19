package com.bit2015.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class JoinFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		WebUtil.forward(request, response, "/views/user/joinform.jsp");
	}
	
}
