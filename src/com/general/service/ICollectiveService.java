package com.general.service;

import java.util.List;

import com.general.model.Collective;
import com.general.model.Resident;

public interface ICollectiveService extends IBaseService<Collective, String> {
	public Collective findByCollectiveName(String collective_name);
	public int batchUpdateResidentCollectiveId(List<String> members_id, Collective collective);
	public List<Resident> searcCollectiveMember(String collective_name);
	public boolean deleteCollectiveMember(String member_id);
}
