package com.general.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.ICollectiveDAO;
import com.general.dao.IResidentDAO;
import com.general.dao.imp.CollectiveDAO;
import com.general.dao.imp.ResidentDAO;
import com.general.model.Collective;
import com.general.model.Resident;
import com.general.service.ICollectiveService;

public class CollectiveService extends BaseService<Collective, String> implements ICollectiveService {
	private ICollectiveDAO collectiveDAO = new CollectiveDAO();
	{
		super.setBaseDAO(collectiveDAO);
	}
	@Override
	public Collective findByCollectiveName(String collective_name) {
		// TODO Auto-generated method stub
		String hql = "from Collective where collective_name = :collective_name";
		Map<String, Object> params = new HashMap<>();
		params.put("collective_name", collective_name);
		List<Collective> lists = collectiveDAO.find(hql, params);
		Collective collective = null;
		if(lists.size() > 0) {
			collective = lists.get(0);
		}
		return collective;
	}
	@Override
	public int batchUpdateResidentCollectiveId(List<String> members_id, Collective collective) {
		// TODO Auto-generated method stub
		String collective_id = collective.getCollective_id();
		IResidentDAO residentDAO = new ResidentDAO();
		int flag = 0;
		for(int i=0; i<members_id.size(); i++) {
			Resident resident = residentDAO.findById(members_id.get(i));
			if (resident != null) {
				if (resident.getCollective_id() == null) {
					resident.setCollective_id(collective_id);
					residentDAO.update(resident);
					flag++;
				}
			}
		}
		return flag;
	}
	@Override
	public List<Resident> searcCollectiveMember(String collective_name) {
		// TODO Auto-generated method stub
		Collective collective = this.findByCollectiveName(collective_name);
		IResidentDAO residentDAO = new ResidentDAO();
		String hql = "from Resident where collective_id = :collective_id";
		Map<String, Object> params = new HashMap<>();
		params.put("collective_id", collective.getCollective_id());
		List<Resident> lists = residentDAO.find(hql, params);
		return lists;
	}
	@Override
	public boolean deleteCollectiveMember(String member_id) {
		// TODO Auto-generated method stub
		IResidentDAO residentDAO = new ResidentDAO();
		Resident resident = residentDAO.findById(member_id);
		resident.setCollective_id(null);
		residentDAO.update(resident);
		return true;
	}
	
}
