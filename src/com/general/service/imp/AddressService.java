package com.general.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.IAddressDAO;
import com.general.dao.imp.AddressDAO;
import com.general.model.Address;
import com.general.service.IAddressService;

public class AddressService extends BaseService<Address, String> implements IAddressService {
	private IAddressDAO addressDAO = new AddressDAO();
	{
		super.setBaseDAO(addressDAO);
	}
	@Override
	public List<Address> findByHouseholdId(String household_id) {
		// TODO Auto-generated method stub
		String hql = "from Address where householder_id = :householder_id";
		Map<String, Object> params = new HashMap<>();
		params.put("householder_id", household_id);
		List<Address> addresses = addressDAO.find(hql, params);
		return addresses;
	}
	@Override
	public List<Address> findByCollectiveId(String collective_id) {
		// TODO Auto-generated method stub
		String hql = "from Address where collective_id = :collective_id";
		Map<String, Object> params = new HashMap<>();
		params.put("collective_id", collective_id);
		List<Address> addresses = addressDAO.find(hql, params);
		return addresses;
	}
	@Override
	public boolean setHouseholdEvacuationDate(String household_id) {
		// TODO Auto-generated method stub
		String hql = "from Address where householder_id = :householder_id order by immigratory_date desc";//日期降序
		Map<String, Object> params = new HashMap<>();
		params.put("householder_id", household_id);
		List<Address> addresses = addressDAO.find(hql, params);
		if(addresses.size() > 0) {
			Address address = addresses.get(0);
			address.setEvacuation_date(new Date());
			addressDAO.update(address);
		}
		return true;
	}
	@Override
	public boolean setCollectiveEvacuationDate(String collective_id) {
		// TODO Auto-generated method stub
		String hql = "from Address where collective_id = :collective_id order by immigratory_date desc";//日期降序
		Map<String, Object> params = new HashMap<>();
		params.put("collective_id", collective_id);
		List<Address> addresses = addressDAO.find(hql, params);
		if(addresses.size() > 0) {
			Address address = addresses.get(0);
			address.setEvacuation_date(new Date());
			addressDAO.update(address);
		}
		return true;
	}

}
