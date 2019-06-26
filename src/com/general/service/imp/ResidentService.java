package com.general.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.IResidentDAO;
import com.general.dao.imp.ResidentDAO;
import com.general.model.Resident;
import com.general.service.IResidentService;

public class ResidentService extends BaseService<Resident, String> implements IResidentService {
	IResidentDAO residentDAO = new ResidentDAO();
	{
		super.setBaseDAO(residentDAO);
	}
	@Override
	public List<Resident> findByName(String name) {
		// TODO Auto-generated method stub
		String hql = "from Resident where name = :name";
		Map<String, Object> params = new HashMap<>();
		List<Resident> residents = residentDAO.find(hql, params);
		return residents;
	}
}
