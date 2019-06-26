package com.general.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general.model.Log;
import com.general.model.Resident;
import com.general.model.User;
import com.general.service.ILogService;
import com.general.service.IResidentService;
import com.general.service.IUserService;
import com.general.service.imp.LogService;
import com.general.service.imp.ResidentService;
import com.general.service.imp.UserService;

/**
 * Servlet implementation class UserManage
 */
@WebServlet(description = "管理员信息管理", urlPatterns = { "/servlet/user" })
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("func");
		switch (param) {
		case "searchUserByAccount":
			searchUserByAccount(request, response);
			ILogService logService1 = new LogService();
			Log log1 = new Log();
			log1.setDate(new Date());
			log1.setOperation("searchUserByAccount");
			logService1.insert(log1);
			break;
		case "deleteUserByAccount":
			deleteUserByAccount(request, response);
			ILogService logService2 = new LogService();
			Log log2 = new Log();
			log2.setDate(new Date());
			log2.setOperation("deleteUserByAccount");
			logService2.insert(log2);
			break;
		default:
			break;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = request.getParameter("func");
		
		// 拓展权限认证
				switch (param) {
				case "searchUserAll":
					searchUserAll(request, response);
					ILogService logService = new LogService();
					Log log = new Log();
					log.setDate(new Date());
					log.setOperation("searchUserAll");
					logService.insert(log);
					break;
				case "searchUserByAccount":
					searchUserByAccount(request, response);
					ILogService logService1 = new LogService();
					Log log1 = new Log();
					log1.setDate(new Date());
					log1.setOperation("searchUserByAccount");
					logService1.insert(log1);
					break;
				case "searchUserOrderRule":
					searchUserOrderRule(request, response);
					ILogService logService2 = new LogService();
					Log log2 = new Log();
					log2.setDate(new Date());
					log2.setOperation("searchUserOrderRule");
					logService2.insert(log2);
					break;
				case "addUser":
					addUser(request, response);
					ILogService logService3 = new LogService();
					Log log3 = new Log();
					log3.setDate(new Date());
					log3.setOperation("addUser");
					logService3.insert(log3);
					break;
				case "deleteUserByAccount":
					deleteUserByAccount(request, response);
					ILogService logService4 = new LogService();
					Log log4 = new Log();
					log4.setDate(new Date());
					log4.setOperation("deleteUserByAccount");
					logService4.insert(log4);
					break;
				case "updateUser":
					updateUser(request, response);
					ILogService logService5 = new LogService();
					Log log5 = new Log();
					log5.setDate(new Date());
					log5.setOperation("updateUser");
					logService5.insert(log5);
					break;
				default:
					break;
				}
	}
	
	public void searchUserAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService userService = new UserService();
		List<User> users = userService.findAll();
		
		if (users.size() > 0) {
			request.setAttribute("message", "scessfully");
			request.setAttribute("users", users);
		} else {
			request.setAttribute("message", "not find");
		}
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/admin_manage/search_result.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void searchUserByAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");

		IUserService userService = new UserService();
		User user = userService.findByAccount(account);
		if (user != null) {
			request.setAttribute("message", "scessfully");
			request.setAttribute("user", user);
		} else {
			request.setAttribute("message", "not find");
		}
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/admin_manage/user_detail.jsp");
		requestDispatcher.forward(request, response);
	}

	public void searchUserOrderRule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 多约束查询
		String account = request.getParameter("account");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String authority_level = request.getParameter("anthority_level");
		
		List<User> users = new ArrayList<>();
		IUserService userService = new UserService();
		if(account != "") {
			User user = userService.findByAccount(account);
			users.add(user);
		}
		if(id != "") {
			User user = userService.findById(id);
			users.add(user);
		}
		if(name != "") {
			IResidentService residentService = new ResidentService();
			List<Resident> residents = residentService.findByName(name);
			for(int i=0; i<residents.size(); i++) {
				User user = userService.findById(residents.get(i).getId());
				users.add(user);
			}
		}
		if(authority_level != "") {
			int authority_level1 = Integer.parseInt(authority_level);
			HttpSession session = request.getSession();
			String uaccount = (String) session.getAttribute("ACCOUNT");
			int authority_level2 = userService.findByAccount(uaccount).getAuthority_level();
			if(authority_level1 > authority_level2) {
				List<User> users2 = userService.findByAuthority_level(authority_level1);
				users.addAll(users2);
			}
		}
		request.setAttribute("users", users);
		
		request.setAttribute("message", "scessfully");
		request.setAttribute("users", users);
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/admin_manage/search_result.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService userService = new UserService();
		User user = getUser(request, response);
		if(user != null) {
			userService.insert(user);
		}
		
		this.searchUserByAccount(request, response);
	}
	
	public void deleteUserByAccount(HttpServletRequest request, HttpServletResponse response) {
		IUserService userService = new UserService();
		String account = request.getParameter("account");
		userService.delete(account);
	}
	
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService userService = new UserService();
		User user = getUser(request, response);
		if(user != null) {
			userService.insert(user);
		}
		
		this.searchUserByAccount(request, response);
	}
	
	public User getUser(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String authority_level = request.getParameter("anthority_level");
		
		
		User user = new User();
		user.setId(id);
		user.setAccount(account);
		user.setPasswd(passwd);
		if(authority_level == null) {
			HttpSession session = request.getSession();
			String uaccount = (String) session.getAttribute("ACCOUNT");
			if(uaccount != null) {
				IUserService userService = new UserService();
				int authority_level2 = userService.findByAccount(uaccount).getAuthority_level()+1;
				System.out.println(authority_level2+"-------");
				user.setAuthority_level(authority_level2);
			}else {
				user = null;
			}
		}else {
			int authority_level1 = Integer.parseInt(authority_level);
			System.out.println(authority_level1+"-------");
			user.setAuthority_level(authority_level1);
		}
		return user;
	}
}
