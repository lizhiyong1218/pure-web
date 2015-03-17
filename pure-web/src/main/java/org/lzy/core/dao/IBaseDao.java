package org.lzy.core.dao;

import java.util.List;

import org.lzy.core.common.Pagination;
import org.lzy.core.common.QueryCondition;


public interface IBaseDao {
	/**
     * 新增实体
     * @param entity  要新增的实体
     */
    public boolean save(Object entity);
    
    /**
     * 批量创建
     * @param <T>
     * @param entitys
     */
    public <T> boolean batchSave(List<T> entitys);
    
    /**
     * 根据主键删除实体
     * @param <T>
     * @param clazz   实体类的Class
     * @param id      主键
     */
    public <T> boolean delete(Class<T> clazz,Object id);
    
    /**
     * 根据主键批量删除实体
     * @param <T>
     * @param clazz   实体类的Class
     * @param id      主键数组
     */
    public <T> boolean batchDelete(Class<T> clazz,Object[] ids);
    
    /**
     * 更新实体
     * @param entity  要更新的实体
     */
    public boolean update(Object entity);
    
    /**
     * 根据主键查询
     * @param <T>
     * @param clazz  实体类的Class
     * @param id     主键
     * @return
     */
    public <T> T getById(Class<T> clazz,Object id);
    
    /**
     * 根据主键数组查询
     * @param <T>
     * @param clazz	 实体类的Class
     * @param ids	 主键数组
     * @return
     */
    public <T> List<T> getByIds(Class<T> clazz, Object[] ids);
    
    /**
     * 查询所有记录
     * @param <T>
     * @param clazz 实体类的Class
     * @return
     */
    public <T> List<T> getAll(Class<T> clazz);
    
    /**
     * 通过jpql语句查询分页记录
     * @param <T>
     * @param clazz
     * @param start
     * @param end
     * @param jpql
     * @param values
     * @return
     */
    public <T> List<T> getListByJpql(Class<T> clazz,int start,int end, String jpql,
    		Object... values);
    
    /**
     * 根据jpql语句查询所有记录
     * @param <T>
     * @param jpql
     * @param objects
     * @return
     */
    public <T> List<T> getListByJpql(String jpql,Object...objects);
    
    /**
     * 根据条件集合查询分页记录
     * @param <T>
     * @param clazz
     * @param queryConditions 查询条件集合
     * @param orderBy         排序,如 order by id desc
     * @param currentPage     当前页
     * @param pageSize        每页显示记录数
     * @return 
     */
    public <T> List<T> getListByConditions(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy,int currentPage,int pageSize);
    
    
    /**
     * 根据条件集合查询所有记录
     * @param <T>
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    public <T> List<T> getListByconditions(Class<T> clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据条件集合查询所有记录并且排序
     * @param <T>
     * @param clazz
     * @param queryConditions  查询条件集合
     * @param orderBy          排序,如 order by id desc
     * @return
     */
    public <T> List<T> getListByConditions(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy);
    
    /**
     * 分页查询
     * @param <T>
     * @param clazz
     * @param queryConditions   查询条件集合
     * @param orderBy           排序字段 如：order by id desc
     * @param currentPage       当前页
     * @param pageSize          每页显示记录数
     * @return
     */
    public <T> Pagination<T> getPagination(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy,int currentPage,int pageSize);
    
    
    /**
     * 根据条件集合查询单条记录
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
	public <T> Object getSingleResult(Class<T> clazz,List<QueryCondition> queryConditions);
    
    /**
     * 查找jpql查询单条记录
     * @param jpql
     * @param objects
     * @return
     */
    public Object getSingleResultByJpql(String jpql,Object...objects);
    
    /**
     * 根据条件查询记录数量
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    public <T> long getRecordCount(Class<T> clazz,List<QueryCondition> queryConditions);
    
    
    /**
     * 执行jpql语句
     * @param jpql
     * @param objects
     * @return
     */
    public int executeJpql(String jpql,Object... objects);
    
}
