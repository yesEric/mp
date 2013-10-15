package com.eric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.dao.UserDao;
import com.eric.dom.User;
import com.eric.service.UserExistsException;
import com.eric.service.UserManager;


/**
 * UserManager 实现类.
 *
 *
 */
@Service("userManager")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager {
    
    private UserDao userDao;
    

    @Autowired
    public void setUserDao(UserDao userDao) {       
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser(String userId) {
        return userDao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) throws UserExistsException {
        try {
            return userDao.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(User user) {
        log.debug("removing user: " + user);
        userDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) {
        log.debug("removing user: " + userId);
        userDao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername(String username)  {
        return (User) userDao.loadUserByUserName(username);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> search(String searchTerm) {
        return super.search(searchTerm, User.class);
    }
}
