package com.general.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseService<T, ID extends Serializable> {
	//基础的增删改查业务
	public boolean insert(T entity);
	public boolean delete(ID id);
	public boolean update(T entity);
	public T findById(ID id);
	public List<T> find(String hql, Map<String, Object> params);
	public List<T> find(String hql);
	public List<T> findAll();
}
