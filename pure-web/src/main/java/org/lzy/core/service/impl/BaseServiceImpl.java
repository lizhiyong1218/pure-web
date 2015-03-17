package org.lzy.core.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.lzy.core.common.Pagination;
import org.lzy.core.common.QueryCondition;
import org.lzy.core.dao.IBaseDao;
import org.lzy.core.service.IBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("baseService")
public class BaseServiceImpl   implements IBaseService {

	@Resource
	private IBaseDao baseDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(Object entity) {
		return baseDao.save(entity);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public <T> boolean batchSave(List<T> entitys) {
		return baseDao.batchSave(entitys);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public <T> boolean delete(Class<T> clazz, Object id) {
		return baseDao.delete(clazz, id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public <T> boolean batchDelete(Class<T> clazz, Object[] ids) {
		return baseDao.batchDelete(clazz, ids);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean update(Object entity) {
		return baseDao.update(entity);
	}

	public <T> T getById(Class<T> clazz, Object id) {
		return baseDao.getById(clazz, id);
	}

	public <T> List<T> getByIds(Class<T> clazz, Object[] ids) {
		return baseDao.getByIds(clazz, ids);
	}

	public <T> List<T> getAll(Class<T> clazz) {
		return baseDao.getAll(clazz);
	}

	public <T> List<T> getListByJpql(Class<T> clazz, int start, int end,
			String jpql, Object... values) {
		return baseDao.getListByJpql(clazz, start, end, jpql, values);
	}

	public <T> List<T> getListByJpql(String jpql, Object... objects) {
		return baseDao.getListByJpql(jpql, objects);
	}

	public <T> List<T> getListByConditions(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		return baseDao.getListByConditions(clazz, queryConditions, orderBy, currentPage, pageSize);
	}

	public <T> List<T> getListByconditions(Class<T> clazz,
			List<QueryCondition> queryConditions) {
		return baseDao.getListByconditions(clazz, queryConditions);
	}

	public <T> List<T> getListByConditions(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy) {
		return baseDao.getListByConditions(clazz, queryConditions, orderBy);
	}

	public <T> Pagination<T> getPagination(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		return baseDao.getPagination(clazz, queryConditions, orderBy, currentPage, pageSize);
	}

	public <T> Object getSingleResult(Class<T> clazz,
			List<QueryCondition> queryConditions) {
		return baseDao.getSingleResult(clazz, queryConditions);
	}

	public Object getSingleResultByJpql(String jpql, Object... objects) {
		return baseDao.getSingleResultByJpql(jpql, objects);
	}

	public <T> long getRecordCount(Class<T> clazz,
			List<QueryCondition> queryConditions) {
		return baseDao.getRecordCount(clazz, queryConditions);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int executeJpql(String jpql, Object... objects) {
		return baseDao.executeJpql(jpql, objects);
	}

	 
	
}
