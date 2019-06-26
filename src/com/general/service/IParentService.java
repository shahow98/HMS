package com.general.service;

import java.util.List;
import java.util.Map;

import com.general.model.Parent;
import com.general.model.Resident;

public interface IParentService extends IBaseService<Parent, String> {
	public Map<Resident, String> searchRelationByResidentId(String resident_id);
	public List<Resident> searchChildren(String resident_id);
}
