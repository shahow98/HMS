package com.general.model;

public class User {
	private String id;
	private int authority_level;
	private String passwd;//可以加密
	private String account;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAuthority_level() {
		return authority_level;
	}
	public void setAuthority_level(int authority_level) {
		this.authority_level = authority_level;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
