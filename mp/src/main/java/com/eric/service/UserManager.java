package com.eric.service;

import com.eric.dao.UserDao;
import com.eric.dom.User;


import java.util.List;


/**
 *实现Web层和持久层通信的业务接口.
 *
 */
public interface UserManager extends GenericManager<User, Long> {
    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * 为单元测试提供的助手方法，你可以在测试时设置Dao实现类
     * @param userDao UserDao的实现类
     */
    void setUserDao(UserDao userDao);

    /**
     * 通过User的ID获得User对象
     *
     * @param userId user对象的主键
     * @return User对象
     */
    User getUser(String userId);

    /**
     * 通过username查找User对象.
     * @param username User的username属性值
     * @return User 找到的User对象
     */
    User getUserByUsername(String username);

    /**
     * 查找所有的User对象.
     * @return 所有的User对象
     */
    List<User> getUsers();

    /**
     * 保存用户信息.
     *
     * @param user user的信息
     * @throws UserExistsException 已存在User对象的异常
     * @return user 更新后的User对象.
     */
    User saveUser(User user) throws UserExistsException;

    /**
     * 从数据库中删除User对象
     *
     * @param user 要删除的User对象
     */
    void removeUser(User user);

    /**
     * 通过Id删除用户对象
     *
     * @param userId the user's id
     */
    void removeUser(String userId);

    /**
     * 通过关键字查找User对象.
     * @param searchTerm 查询关键字.
     * @return 所有找到的User对象.
     */
    List<User> search(String searchTerm);
}
