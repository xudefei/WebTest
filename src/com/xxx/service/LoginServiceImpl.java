package com.xxx.service;

import com.opensymphony.xwork2.ActionSupport;

public class LoginServiceImpl implements LoginService{
	
	private String userName = "xxx";
	private String password = "123456";

	@Override
	public String doLogin(String userName, String password) {
		if (this.userName.equals(userName) && this.password.equals(password)) {
			return ActionSupport.SUCCESS;
		}
		return ActionSupport.ERROR;
	}

}
