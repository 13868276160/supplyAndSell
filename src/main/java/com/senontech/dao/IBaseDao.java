package com.senontech.dao;

import java.io.Serializable;

/**
 * DAO公共方法
 */
public interface IBaseDao {

    void save(Object object);

    void update(Object object);

    void remove(Object object);

    void delete(Object object);

    //<T> T get(Class<T> entityType, Serializable id);

    /**
     * 根据主键查询，如果deFlag为已删除，则返回null
     * @param entityType
     * @param id
     * @param <T>
     * @return
     */
    <T> T getByDeFlag(Class<T> entityType, Serializable id);
}
