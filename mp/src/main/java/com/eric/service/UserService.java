package com.eric.service;

import com.eric.dom.User;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * 用于暴露WebService方法的接口，它不继承GenericManager.
 */
@WebService
@Path("/users")
public interface UserService {
    /**
     * 通过userId获取User对象
     *
     * @param userId User的主键
     * @return User对象
     */
    @GET
    @Path("{id}")
    User getUser(@PathParam("id") String userId);

    /**
     * 通过username查找User对象.
     *
     * @param username 用户的username
     * @return 找到的User对象
     */
    User getUserByUsername(@PathParam("username") String username);

    /**
     * 查找所有的User对象.
     *
     * @return List
     */
    @GET
    List<User> getUsers();

    /**
     * 保存User对象信息
     *
     * @param user user对象信息
     * @return 更新后的user对象信息
     * @throws UserExistsException 在用户未找到时抛出的异常
     */
    @POST
    User saveUser(User user) throws UserExistsException;

    /**
     * 通过User的id删除这个User对象
     *
     * @param userIduser's id
     */
    @DELETE
    void removeUser(String userId);
}
