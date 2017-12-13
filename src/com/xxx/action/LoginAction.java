package com.xxx.action;


import com.opensymphony.xwork2.ActionSupport;
import com.xxx.service.LoginService;

public class LoginAction extends ActionSupport{

	private String userName;
	private String password;
	private LoginService loginService;
	
	
	
	
	public LoginService getLoginService() {
		return loginService;
	}


	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public String execute() throws Exception {
		return loginService.doLogin(userName, password);
	}

}
