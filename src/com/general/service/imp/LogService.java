package com.general.service.imp;


import com.general.dao.ILogDAO;
import com.general.dao.imp.LogDAO;
import com.general.model.Log;
import com.general.service.ILogService;

public class LogService extends BaseService<Log, String> implements ILogService {
	private ILogDAO logDAO = new LogDAO();
	{
		super.setBaseDAO(logDAO);
	}
}
