package com.semsari.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImp<T> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;

	}

	private Class<T> type;
    @SuppressWarnings("unchecked")
	public GenericDaoImp() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
		logger = LoggerFactory.getLogger(type);
	}

	@Override
    @SuppressWarnings("unchecked")
	public T create( T t) {
		Session session = this.sessionFactory.getCurrentSession();
		t = (T) session.merge(t);
		logger.debug("create object: " + t.toString());
		return t;
	}
	
	@Override
	public Integer save( T t) {
		Session session = this.sessionFactory.getCurrentSession();
		Integer ret = (Integer) session.save(t);
		logger.debug("save object: " + t.toString());
		return ret;
	}


	@Override
	public void delete(final Object id) {
		Session session = this.sessionFactory.getCurrentSession();
		T p = find( (Integer)id);
		if (null != p) {
			session.delete(p);
			logger.debug("delete object: " + p.toString());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(int id) {
		
		return (T) this.sessionFactory.getCurrentSession().get(type, id);
/*		Session session = null;
		T obj = null;
		try {
			session = this.sessionFactory.openSession();
			obj = (T) session.load(type, id);
			Hibernate.initialize(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		logger.info("find object: " + obj.toString());
		return obj;*/
	}

	@Override
	public T update(final T t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		logger.debug("update object: " + t.toString());
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		//return session.createCriteria(type).list();
		return session.createQuery("from "+ type.getSimpleName()).list();
	}

}