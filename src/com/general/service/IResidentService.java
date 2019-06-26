package com.general.service;

import java.util.List;

import com.general.model.Resident;

public interface IResidentService extends IBaseService<Resident, String> {
	public List<Resident> findByName(String name);
}
