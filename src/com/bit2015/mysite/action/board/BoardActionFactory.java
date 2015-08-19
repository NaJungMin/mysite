package com.bit2015.mysite.action.board;

import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {

			Action action = null;
			if("write".equals(actionName)){
				action = new WriteAction();
			}else if("writeform".equals(actionName)){
				action = new WriteFormAction();
			}else if("delete".equals(actionName)){
				action = new DeleteAction();
			}else if("update".equals(actionName)){
				action = new UpdateAction();
			}else if("updateform".equals(actionName)){
				action = new UpdateFormAction();
			}else if("reply".equals(actionName)){
				action = new ReplyAction();
			}else if("view".equals(actionName)){
				action = new ViewAction();
			}else if("search".equals(actionName)){
				action = new SearchAction();
			}else if("redelete".equals(actionName)){
				action = new ReDeleteAction();
			}else if("dap".equals(actionName)){
				action = new DapAction();
			}else if("dapform".equals(actionName)){
				action = new DapFormAction();
			}else {
				action  = new IndexAction();
			}
		
		return action;
	}

}
