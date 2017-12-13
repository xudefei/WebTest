package com.xxx.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class BaseDaoHibernate3<T> implements BaseDao<T> {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public T get(Class<T> entityClass, Serializable id) {
		return getSessionFactory().getCurrentSession().get(entityClass, id);
	}

	@Override
	public Serializable save(T entity) {
		SessionFactory sf =	getSessionFactory();
		@SuppressWarnings("unused")
		Session s = sf.getCurrentSession();
		return getSessionFactory().getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	@Override
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<T> entityClass, Serializable id) {
		getSessionFactory().getCurrentSession()
				.createQuery("delet " + entityClass.getSimpleName() + " en where en.id=?0").setParameter(0, id)
				.executeUpdate();
	}

	@Override
	public List<T> findAll(Class<T> entityClass) {
		return (List<T>) find("select en from " + entityClass.getSimpleName() + " en");
	}

	@SuppressWarnings("unchecked")
	@Override
	public long findCount(Class<T> entityClass) {
		List<Long> list = (List<Long>) find("select count(*) from " + entityClass.getSimpleName() + " en");

		if (list != null && list.size() == 1) {
			return (long) list.get(0);
		}
		return 0;
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findBypage(String hql, final int pageNo, final int pageSize) {
		List<T> list = getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();

		return list;

	}

	/**
	 * 带占位符的分页查询
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> findBypage(String hql, int pageNo, int pageSize, Object... params) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> find(String hql) {
		return getSessionFactory().getCurrentSession().createQuery(hql).list();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> find(String hql, Object... paramas) {
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i = 0; i < paramas.length; i++) {
			query.setParameter(i, paramas[i]);
		}

		return query.list();
	}
}
