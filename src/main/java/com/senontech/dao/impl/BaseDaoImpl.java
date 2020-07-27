package com.senontech.dao.impl;

import com.senontech.enums.DeFlagEnum;
import com.senontech.dao.IBaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BaseDaoImpl implements IBaseDao {
    @Autowired
    protected SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }



    @Override
    public void save(Object object) {
        getSession().save(object);
    }

    @Override
    public void update(Object object) {
        getSession().update(object);
    }

    @Override
    public void remove(Object object) {
        getSession().remove(object);
    }

    @Override
    public void delete(Object object) {
        getSession().delete(object);
    }

//    @Override
//    public <T> T get(Class<T> entityType, Serializable id) {
//        return getSession().get(entityType, id);
//    }

    @Override
    public <T> T getByDeFlag(Class<T> entityType, Serializable id) {
        T t = getSession().get(entityType, id);
        if(t==null) return t;
        try {
            Field f = entityType.getDeclaredField("deFlag");
            f.setAccessible(true);
            //如果删除标记是yes，则返回null
            if( DeFlagEnum.YES.getCode().equals(f.get(t))){
                return null;
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
        return t;
    }

    public void setQueryParameters(Query query, List<Object> params) {
        for (int i = 0, l = params.size(); i < l; i++) {
            query.setParameter(i, params.get(i));
        }
    }

}
