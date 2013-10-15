package com.eric.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.eric.dao.GenericDao;
import com.eric.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务层完成CRUD操作的基类，如果需要自定义方法可以实现这个基类.
 *
 * @param <T>  实体对象类型
 * @param <PK> 实体对象主键
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
    /**
     * 日志变量
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao实例，在构造函数中北实例化.
     */
    protected GenericDao<T, PK> dao;


    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        dao.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.remove(id);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * 使用Hibernate Search实现，调用Dao中的方法.
     */
    @SuppressWarnings("unchecked")
    public List<T> search(String q, Class clazz) {
        if (q == null || "".equals(q.trim())) {
            return getAll();
        }

        return dao.search(q);
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        dao.reindex();
    }

    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        dao.reindexAll(async);
    }
}
