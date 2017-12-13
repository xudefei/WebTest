package com.xxx.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoHibernate4<T> extends HibernateDaoSupport implements BaseDao<T>{
	


	@Override
	public T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<T> entityClass, Serializable id) {
		getHibernateTemplate().delete(get(entityClass, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> entityClass) {
		return (List<T>) getHibernateTemplate().find("select en from " + entityClass.getSimpleName() + " en");
	}

	@SuppressWarnings("unchecked")
	@Override
	public long findCount(Class<T> entityClass) {
		List<Long> list = (List<Long>) getHibernateTemplate().find("select count(*) from " + entityClass.getSimpleName()+" en");
		
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
	protected List<T> findBypage(final String hql,final int pageNo,final int pageSize) {
		List<T> list =  getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				List<T> result = session.createQuery(hql).setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();
				return result;
			}
		});

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
	protected List<T> findBypage(final String hql, final int pageNo, final int pageSize, final Object... params) {
		List<T> list = getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql).setFirstResult((pageNo - 1)*pageSize)
						.setMaxResults(pageSize);
				
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				 List<T> result = query.list();
				return result;
			}
		});
		return list;
	}
}
