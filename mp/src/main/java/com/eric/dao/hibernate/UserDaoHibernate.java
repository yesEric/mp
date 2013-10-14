package com.eric.dao.hibernate;

import com.eric.dao.UserDao;
import com.eric.dom.User;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * 对User对象进行CRUD的类.
 * @Repository 注解用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
 * 具体只需将该注解标注在 DAO 类上即可。同时，为了让 Spring 能够扫描类路径中的类并识别出 @Repository 注解，
 * 需要在 XML 配置文件中启用 Bean 的自动扫描功能，这可以通过 <context:component-scan/> 实现。
 *
*/
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

    /**
     *在构造方法中设置 User.class为实体对象.
     */
    public UserDaoHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        getSession().saveOrUpdate(user);
        
        getSession().flush();
        return user;
    }

    /**
     *
     * 重载基类的save 方法，因为saveUser方法会强制刷新缓存，但是基类的save方法不会
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
    */
    public User loadUserByUserName(String username) {
        List<User> users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
        return users.get(0);
    }


   
}
