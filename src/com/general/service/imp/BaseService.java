package com.general.service.imp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.general.dao.IBaseDAO;
import com.general.service.IBaseService;

public class BaseService<T, ID extends Serializable> implements IBaseService<T, ID> {

	protected IBaseDAO<T, ID> baseDAO;
	
	public void setBaseDAO(IBaseDAO<T, ID> baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	
	@Override
	public boolean insert(T entity) {
		// TODO Auto-generated method stub
		return this.baseDAO.insert(entity);
	}

	@Override
	public boolean delete(ID id) {
		// TODO Auto-generated method stub
		return this.baseDAO.delete(id);
	}

	@Override
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return this.baseDAO.update(entity);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.baseDAO.find(hql, params);
	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return this.baseDAO.findById(id);
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return this.baseDAO.find(hql);
	}
	
	@Override
	public List<T> findAll() {
		return this.baseDAO.findAll();
	}
}