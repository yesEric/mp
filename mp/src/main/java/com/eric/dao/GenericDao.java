package com.eric.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.search.SearchException;


/**
 * Generic DAO (Data Access Object) 用于完成针对POJO对象的通用的CRUD.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) DAO's for your
 * domain objects.
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
     * @param searchTerm the term to search for
     * @return the matching records
     * @throws SearchException
     */
    List<T> search(String searchTerm) throws SearchException;

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the persisted object
     */
    T save(T object);


    /**
     * Generic method to delete an object
     * @param object the object to remove
     */
    void remove(T object);

    /**
     * Generic method to delete an object
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Find a list of records by using a named query
     * @param queryName query name of the named query
     * @param queryParams a map of the query names and the values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * Generic method to regenerate full text index of the persistent class T
     */
    void reindex();

    /**
     * Generic method to regenerate full text index of all indexed classes
     * @param async true to perform the reindexing asynchronously
     */
    void reindexAll(boolean async);
}