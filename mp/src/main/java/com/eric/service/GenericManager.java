package com.eric.service;

import java.io.Serializable;
import java.util.List;

/**
 * GenericManager 用于处理 GenericDao 中的 CRUD 操作.
 *
 * <p>如果需要自定义的操作请继承这个接口.
 * @param <T> POJO类型变量
 * @param <PK> 实体主键
 */
public interface GenericManager<T, PK extends Serializable> {

    /**
     * 获得表中所有记录的通用方法.
     * @return 所有查询的记录
     */
    List<T> getAll();

    /**
     * 通过实体主键查找实体对象，如果找不到要抛出ObjectRetrievalFailureException异常.
     *
     * @param id 实体对象主键
     * @return 获取的对象
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * 通过实体主键检查实体是否存在.
     * @param id 实体主键
     * @return - true表示存在, false表示不存在
     */
    boolean exists(PK id);

    /**
     * 保存实体对象的方法，包括：新增和更新操作.
     * @param object 要保存的对象
     * @return 保存后的对象
     */
    T save(T object);

    /**
     * 删除实体对象的方法
     * @param object 要删除的对象
     */
    void remove(T object);

    /**
     * 通过实体主键删除对象的方法
     * @param id 要删除的对象的实体主键
     */
    void remove(PK id);

    /**
     * 通过关键字查找对象的方法.
     * @param searchTerm 查询关键字
     * @param clazz 查询实体的类对象.
     * @return 符合查询条件的实体对象
     */
    List<T> search(String searchTerm, Class clazz);
    /**
     * 重新索引实体对象的方法。
     */
    void reindex();

    /**
     * 异步重新索引实体对象的方法。
     *
     * @param async
     *            true 表示异步执行索引操作.
     */
    void reindexAll(boolean async);
}
