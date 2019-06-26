package com.general.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.IUserDAO;
import com.general.dao.imp.UserDAO;
import com.general.model.User;
import com.general.service.IUserService;

public class UserService extends BaseService<User, String> implements IUserService {
	private IUserDAO userDAO = new UserDAO();
	{
		super.setBaseDAO(userDAO);
	}
	@Override
	public int loginVaild(String account, String passwd) {
		// TODO Auto-generated method stub
		String hql = "from User where account = :account and passwd = :passwd";
		Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		params.put("passwd", passwd);
		List<User> users = userDAO.find(hql, params);
		int flag = 0;
		if(users.size() == 1){
			flag = 1;
		}
		return flag;
	}
	@Override
	public User findByAccount(String account) {
		// TODO Auto-generated method stub
		User user = null;
		String hql = "from User where account = :account";
		Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		List<User> users = userDAO.find(hql, params);
		if(users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}
	@Override
	public boolean deleteByAccount(String account) {
		// TODO Auto-generated method stub
		boolean flag = false;
		User user = this.findByAccount(account);
		if(user != null) {
			flag = this.delete(user.getId());
		}
		return flag;
	}
	@Override
	public List<User> findByAuthority_level(int authority_level) {
		// TODO Auto-generated method stub
		String hql = "from User where authority_level = :authority_level";
		Map<String, Object> params = new HashMap<>();
		params.put("authority_level", authority_level);
		List<User> users = userDAO.find(hql, params);
		return users;
	}
}
