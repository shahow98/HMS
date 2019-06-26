package com.general.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//日志
public class Log {
	private String log_id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String account;
	private String operation;
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}
