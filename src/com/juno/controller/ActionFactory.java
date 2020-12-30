package com.juno.controller;

import com.juno.controller.action.Action;
import com.juno.controller.action.Delete_Action;
import com.juno.controller.action.Idcheck_Action;
import com.juno.controller.action.Join_Action;
import com.juno.controller.action.Join_Form_Action;
import com.juno.controller.action.Login_Action;
import com.juno.controller.action.Login_Form_Action;
import com.juno.controller.action.Logout_Action;
import com.juno.controller.action.SelectAll_Action;
import com.juno.controller.action.Update_Action;
import com.juno.controller.action.Update_Form_Action;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory ist = new ActionFactory();
	public static ActionFactory getIst() { return ist; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if (command.equals("login_form")) ac = new Login_Form_Action();
		else if (command.equals("login")) ac = new Login_Action();
		else if (command.equals("join_form")) ac = new Join_Form_Action();
		else if (command.equals("idcheck")) ac = new Idcheck_Action();
		else if (command.equals("join")) ac = new Join_Action();
		else if (command.equals("delete")) ac = new Delete_Action();
		else if (command.equals("logout")) ac = new Logout_Action();
		else if (command.equals("select_all")) ac = new SelectAll_Action();
		else if (command.equals("update_form")) ac = new Update_Form_Action();
		else if (command.equals("update")) ac = new Update_Action();
		
		return ac;
	}
}
