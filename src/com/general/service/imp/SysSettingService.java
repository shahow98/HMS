package com.general.service.imp;

import com.general.dao.ISysSettingDAO;
import com.general.dao.imp.SysSettingDAO;
import com.general.model.SysSetting;
import com.general.service.ISysSettingService;

public class SysSettingService extends BaseService<SysSetting, String> implements ISysSettingService {
	private ISysSettingDAO sysSettingDAO = new SysSettingDAO();
	{
		super.setBaseDAO(sysSettingDAO);
	}
}
