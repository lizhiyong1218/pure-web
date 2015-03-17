package org.lzy.core.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lzy.core.common.Pagination;
import org.lzy.core.common.QueryCondition;
import org.lzy.core.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao{
	
	private static final Log logger=LogFactory.getLog(BaseDaoImpl.class);
	@PersistenceContext
	EntityManager em;
	
	public boolean save(Object entity) {
		 try {
			em.persist(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public <T> boolean batchSave(List<T> entitys){
		try {
			for(T entity : entitys){
				em.persist(entity);
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public <T> boolean delete(Class<T> clazz, Object id) {
		try {
			T entity = em.find(clazz, id);
			em.remove(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public <T> boolean batchDelete(Class<T> clazz, Object[] ids) {
		try {
			T entity = null;
			for (Object id : ids) {
				entity = em.find(clazz, id);
				em.remove(entity);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Object entity) {
		 try {
			em.merge(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public <T> T getById(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}
	
	public <T> List<T> getByIds(Class<T> clazz, Object[] ids){
		List<T> list = new ArrayList<T>();
		for(Object id : ids){
			list.add(getById(clazz, id));
		}
		return list;
	}

	public <T> List<T> getAll(Class<T> clazz) {
		String className = clazz.getSimpleName();
		StringBuffer jpql = new StringBuffer("select o from ");
		jpql.append(className).append(" o ");
		return em.createQuery(jpql.toString()).getResultList();
	}

	public <T> List<T> getListByJpql(Class<T> clazz, int start, int end,
			String jpql, Object... values) {
		 Query query = em.createQuery(jpql);
    	 if(values!=null){
    		for(int i=0;i<values.length;i++){
    			query.setParameter(i+1, values[i]);
    		}
    	 }
    	 query.setFirstResult(start);
    	 query.setMaxResults(end);
    	 List<T> list=new ArrayList<T>();
    	 list=query.getResultList();
    	 return list;
	}

	public <T> List<T> getListByJpql(String jpql, Object... objects) {
		Query query = em.createQuery(jpql);
		if (objects != null) {
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i + 1, objects[i]);
				}
			}
		}
		return query.getResultList();
	}

	public <T> List<T> getListByConditions(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		Query query = getQuery(clazz, queryConditions, orderBy, false);
		if (currentPage == 0 && pageSize == 0) {
			return query.getResultList();
		} else {
			return query.setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize).getResultList();
		}
	}

	public <T> List<T> getListByconditions(Class<T> clazz, List<QueryCondition> queryConditions) {
		return getListByConditions(clazz, queryConditions, null, 0, 0);
	}

	public <T> List<T> getListByConditions(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy) {
		return getListByConditions(clazz, queryConditions, orderBy, 0, 0);
	}

	public <T> Pagination<T> getPagination(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		List<T> recordList = getListByConditions(clazz, queryConditions, orderBy, currentPage,
				pageSize);
		long recordCount = getRecordCount(clazz, queryConditions);
		return new Pagination(currentPage, pageSize, recordCount, recordList);
	}

	public <T> Object getSingleResult(Class<T> clazz,
			List<QueryCondition> queryConditions) {
		Query query = getQuery(clazz, queryConditions, null, false);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Object getSingleResultByJpql(String jpql, Object... objects) {
		Query query = em.createQuery(jpql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// throw new RuntimeException("这里是JPA方法抛出的异常，应属设计问题！",e);
			return null;
		}
	}

	public <T> long getRecordCount(Class<T> clazz, List<QueryCondition> queryConditions) {
		Query query = getQuery(clazz, queryConditions, null, true);
		Object result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		long recordCount = 0L;
		if (result != null) {
			recordCount = ((Long) result).longValue();
		}
		return recordCount;
	}

	public int executeJpql(String jpql, Object... objects) {
		Query query = em.createQuery(jpql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}
		return query.executeUpdate();
	}
	
	/**
	 * 根据查询条件获取Query
	 * 
	 * @param clazz
	 * @param queryConditions
	 * @param orderBy
	 * @param isQueryTotal
	 *            是否查询记录总数, true 则查询记录总数
	 * @return
	 */
	private Query getQuery(Class clazz, List<QueryCondition> queryConditions,
			String orderBy, boolean isQueryTotal) {
		String className = clazz.getSimpleName();
		String preJPQL = isQueryTotal ? "select count(*) from "
				: "select o from ";
		StringBuffer jpql = new StringBuffer(preJPQL);
		jpql.append(className).append(" o where 1=1 ");
		Query query = null;
		if (queryConditions != null && queryConditions.size() > 0) {
			// 构造jpql语句
			Iterator<QueryCondition> iterator = queryConditions.iterator();
			while (iterator.hasNext()) {
				QueryCondition queryCondition = iterator.next();
				if (queryCondition != null) {
					if (queryCondition.getOperator().equals(
							QueryCondition.CUSTOM)) {
						jpql.append(" and (").append(
								queryCondition.getCustomJPQL()).append(")");
					}
					if (queryCondition.getValue() != null
							&& !"".equals(queryCondition.getValue())) {
						// 如果占位符名称是*.*格式，则换成*_*格式。且：和名称之间不能有空格
						String placeholder = queryCondition.getField().indexOf(
								".") != -1 ? queryCondition.getField().replace(
								".", "_") : queryCondition.getField();
						jpql.append(" and o.").append(
								queryCondition.getField().trim()).append(" ")
								.append(queryCondition.getOperator()).append(
										":").append(placeholder.trim());
					}
				}

			}
		}
		if (orderBy != null && !"".equals(orderBy)) {
			jpql.append(" ").append(orderBy);
		}

		query = em.createQuery(jpql.toString());

		if (queryConditions != null && queryConditions.size() > 0) {
			// 为参数赋值
			Iterator<QueryCondition> iterator2 = queryConditions.iterator();
			while (iterator2.hasNext()) {
				QueryCondition queryCondition = iterator2.next();
				if (queryCondition != null) {
					if (queryCondition.getValue() != null
							&& !"".equals(queryCondition.getValue())) {
						// 将占位符中的.替换成_
						queryCondition.setField(queryCondition.getField()
								.indexOf(".") != -1 ? queryCondition.getField()
								.replace(".", "_") : queryCondition.getField());
						if (queryCondition.getOperator().equals(
								QueryCondition.LK)) {
							query.setParameter(queryCondition.getField(), "%"
									+ queryCondition.getValue() + "%");
						} else {
							query.setParameter(queryCondition.getField(),
									queryCondition.getValue());
						}
					}
				}

			}
		}
		return query;
	}

}
