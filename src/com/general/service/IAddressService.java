package com.general.service;

import java.util.List;

import com.general.model.Address;

public interface IAddressService extends IBaseService<Address, String> {
	public List<Address> findByHouseholdId(String household_id);
	public List<Address> findByCollectiveId(String collective_id);
	public boolean setHouseholdEvacuationDate(String household_id);
	public boolean setCollectiveEvacuationDate(String collective_id);
}
