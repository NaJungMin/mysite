package com.bit2015.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.vo.UserVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class WriteFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		UserVo uvo = (UserVo)session.getAttribute( "authUser" );
		
		if(uvo==null||uvo.equals("")){
			//로그인실패
			WebUtil.redirect(request, response, "board?a=board&error=true");
			return;
			//리다이렉트를 했다고 메서드가 끝나지않음. 반드시 return 구문으로 메서드 종료를 해줘야함.
		}
		WebUtil.forward(request, response, "/views/board/write.jsp");
	
	}

}
