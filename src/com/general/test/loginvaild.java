package com.general.test;

import com.general.service.IUserService;
import com.general.service.imp.UserService;

public class loginvaild {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String account = "root";
		String passwd = "root";
		IUserService userService = new UserService();
		int flag = userService.loginVaild(account, passwd);
		System.out.println(flag);
	}

}
