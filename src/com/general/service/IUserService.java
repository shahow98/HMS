package com.general.service;

import java.util.List;

import com.general.model.User;

public interface IUserService extends IBaseService<User, String> {
	public int loginVaild(String account, String passwd);
	public User findByAccount(String account);
	public List<User> findByAuthority_level(int authority_level);
	public boolean deleteByAccount(String account);
}
