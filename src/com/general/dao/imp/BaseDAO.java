package com.general.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.general.dao.IBaseDAO;
import com.general.util.HibernateSessionFactory;

public class BaseDAO <T, ID extends Serializable> implements IBaseDAO<T, ID>{

	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDAO() {
		// TODO Auto-generated constructor stub
		entityClass = null;
		Class<?> c = this.getClass(); //获得当前接口实现类
		Type type = c.getGenericSuperclass(); //获得该类的直接超类
		if(type instanceof ParameterizedType) {//判断是否为参数化类型
			//获得此类型实际类型参数的 Type对象参数
			Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();
			//转为Class并赋值
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}
	
	@Override
	public boolean insert(T entity) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);//如果主键相同则更新，不同则插入
		transaction.commit();
		HibernateSessionFactory.closeSession();
		return true;
	}

	@Override
	public boolean delete(ID id) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(findById(id));
		transaction.commit();
		HibernateSessionFactory.closeSession();
		return true;
	}

	@Override
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		HibernateSessionFactory.closeSession();
		return true;
	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		T entity = (T)session.get(this.entityClass, id);
		HibernateSessionFactory.closeSession();
		return entity;
		
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query<?> query = session.createQuery(hql);
		if(params != null && !params.isEmpty()) {
			for(String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> lists = (List<T>) query.list();
		HibernateSessionFactory.closeSession();
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Query<?> query = session.createQuery(hql);
		List<T> lists = (List<T>) query.list();
		HibernateSessionFactory.closeSession();
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String cname = this.entityClass.getSimpleName();
		String hql = "from " + cname;
		Query<?> query = session.createQuery(hql);
		List<T> lists = (List<T>) query.list();
		HibernateSessionFactory.closeSession();
		return lists;
	}
}
