package com.general.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general.model.Log;
import com.general.model.User;
import com.general.service.ILogService;
import com.general.service.IUserService;
import com.general.service.imp.LogService;
import com.general.service.imp.UserService;

/**
 * Servlet implementation class LoginVaild
 */
@WebServlet(description = "登录验证", urlPatterns = { "/servlet/loginvaild" })
public class LoginVaild extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logout(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = request.getParameter("func");
		switch (param) {
		case "login":
			login(request, response);
			ILogService logService = new LogService();
			Log log = new Log();
			log.setDate(new Date());
			log.setOperation("login");
			logService.insert(log);
			break;
		default:
			logout(request, response);
			login(request, response);
			ILogService logService1 = new LogService();
			Log log1 = new Log();
			log1.setDate(new Date());
			log1.setOperation("logout");
			logService1.insert(log1);
			break;
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catetory = request.getParameter("category");
		String account = request.getParameter("account");//账号
		String passwd = request.getParameter("passwd");//密码
		if (catetory == null) {
			request.setAttribute("message", "failed");
			logout(request, response);
		}
		else if(catetory.equals("admin")) {
			IUserService userService = new UserService();
			int flag = userService.loginVaild(account, passwd);
			if(flag == 1) {//登录成功
				request.setAttribute("message", "scessfully");
				
				HttpSession session = request.getSession();
				session.setAttribute("ACCOUNT", account);
				ServletContext application = this.getServletContext();
				RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/welcome.html");
				requestDispatcher.forward(request, response);
			}else {//登录失败
				logout(request, response);
			}
		}else if(catetory.equals("resident")) {
			request.setAttribute("message", "scessfully");
			
			ServletContext application = this.getServletContext();
			RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/resident/welcome.html");
			requestDispatcher.forward(request, response);
		}else {//登录失败
			logout(request, response);
		}
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "failed");
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/login.jsp");
		requestDispatcher.forward(request, response);
	}
}
