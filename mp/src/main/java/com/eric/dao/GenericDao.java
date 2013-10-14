package com.eric.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.search.SearchException;


/**
 * 通用 DAO (Data Access Object)接口， 用于完成针对POJO对象的通用的CRUD.
 *
 * <p>如果你想为你的领域对象使用类型安全的DAO，需要继承这个接口.
 *
 * @author <a href="mailto:liuguodong@gmail.com">Eric</a>
 * 
 * @param <T> POJO对象
 * @param <PK> POJO对象的主键
 */
public interface GenericDao <T, PK extends Serializable> {

    /**
     * 查询所有记录
     * @return 所有记录
     */
    List<T> getAll();

    /**
     * 查询所有不重复的记录.
     * <p>注意，如果使用这个方法，那么POJO对象必须实现 hashcode/equals方法</p>
     * @return 所有不重复的记录
     */
    List<T> getAllDistinct();

    /**
     * Search方法可以查询.
     * @param 查询条件
     * @return 匹配的查询结果
     * @throws SearchException
     */
    List<T> search(String searchTerm) throws SearchException;

    /**
     * 获取ID对应的对象.
     * 如果找不到对象会抛出ObjectRetrievalFailureException运行时异常.
     *
     * @param id 对象ID
     * @return 根据ID获取的对象
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * 检查ID对应的对象是否存在.
     * @param id 实体的ID
     * @return - true 表示存在, false 表示不存在
     */
    boolean exists(PK id);

    /**
     * 通用对象保持方法，包括新增和更新.
     * @param 要保存的对象
     * @return 保存后的对象
     */
    T save(T object);


    /**
     * 删除一个对象的通用方法。
     * @param 要删除的对象
     */
    void remove(T object);

    /**
     * 通过对象ID删除对象的方法.
     * @param 实体对象的主键
     */
    void remove(PK id);

    /**
     * 根据命名查询语句进行查询的方法.
     * @param queryName 查询语句的名称
     * @param queryParams 查询语句的参数列表
     * @return 查询结果集合
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * 为当前对象生成全文查找索引的通用方法
     */
    void reindex();

    /**
     * 将所有已索引对象重新索引的方法
     * @param async true 表示异步执行
     */
    void reindexAll(boolean async);
}