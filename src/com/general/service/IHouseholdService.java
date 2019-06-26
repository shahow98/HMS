package com.general.service;

import java.util.List;

import com.general.model.Household;
import com.general.model.Resident;

public interface IHouseholdService extends IBaseService<Household, String> {
	public Household findByPersonalId(String personal_id);
	public int batchUpdateResidentHouseholdId(List<String> members_id, Household household);
	public List<Resident> searcHouseholdMember(String personal_id);
	public boolean deleteHouseholdMember(String member_id);
	public Household searchHousehold(String member_id);
}
