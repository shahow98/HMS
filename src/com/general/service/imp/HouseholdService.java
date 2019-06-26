package com.general.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.IHouseholdDAO;
import com.general.dao.IResidentDAO;
import com.general.dao.imp.HouseholdDAO;
import com.general.dao.imp.ResidentDAO;
import com.general.model.Household;
import com.general.model.Resident;
import com.general.service.IHouseholdService;

public class HouseholdService extends BaseService<Household, String> implements IHouseholdService {
	private IHouseholdDAO householdDAO = new HouseholdDAO();
	{
		super.setBaseDAO(householdDAO);
	}
	public int batchUpdateResidentHouseholdId(List<String> members_id, Household household) {
		String household_id = household.getHouseholder_id();
		IResidentDAO residentDAO = new ResidentDAO();
		int flag = 0;
		for(int i=0; i<members_id.size(); i++) {
			Resident resident = residentDAO.findById(members_id.get(i));
			if(resident != null) {
				if(resident.getHouseholder_id() == null) {
					resident.setHouseholder_id(household_id);
					residentDAO.update(resident);
					flag++;
				}	
			}	
		}
		return flag;
	}

	@Override
	public Household findByPersonalId(String personal_id) {
		// TODO Auto-generated method stub
		String hql = "from Household where personal_id = :personal_id";
		Map<String, Object> params = new HashMap<>();
		params.put("personal_id", personal_id);
		List<Household> lists = householdDAO.find(hql, params);
		Household household = null;
		if(lists.size() > 0) {
			household = lists.get(0);
		}
		return household;
	}

	@Override
	public List<Resident> searcHouseholdMember(String personal_id) {
		// TODO Auto-generated method stub
		IResidentDAO residentDAO = new ResidentDAO();
		System.out.println(personal_id+"----------");
		Resident resident = residentDAO.findById(personal_id);
		String hql = "from Resident where householder_id = :householder_id";
		Map<String, Object> params = new HashMap<>();
		params.put("householder_id", resident.getHouseholder_id());
		List<Resident> lists = residentDAO.find(hql, params);
		return lists;
	}

	@Override
	public boolean deleteHouseholdMember(String member_id) {
		// TODO Auto-generated method stub
		IResidentDAO residentDAO = new ResidentDAO();
		Resident resident = residentDAO.findById(member_id);
		resident.setHouseholder_id(null);
		residentDAO.update(resident);
		return true;
	}

	@Override
	public Household searchHousehold(String member_id) {
		// TODO Auto-generated method stub
		IResidentDAO residentDAO = new ResidentDAO();
		Resident resident = residentDAO.findById(member_id);
		Household household = householdDAO.findById(resident.getHouseholder_id());
		return household;
	}

}
