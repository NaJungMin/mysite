package com.bit2015.mysite.action.index;
import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;


public class IndexActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {

		return  new IndexAction();
	}

}
