package com.eric.dao;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 * Dao测试的基类.
 *
 */
@ContextConfiguration(
        locations = {"classpath:/applicationContext-resources.xml",
                "classpath:/applicationContext-dao.xml",
                "classpath*:/applicationContext.xml",
                "classpath:**/applicationContext*.xml"})
public abstract class BaseDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 声明日志变量
     */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * 加载资源绑定，默认照from src/test/resources/${package.name}/ClassName.properties（如果存在）
     */
    protected ResourceBundle rb;

    /**
     * 缺省构造器用于加载类对应的资源文件，这里只是检查是否src/test/resources下存在要绑定的资源文件。
     * 
     */
    public BaseDaoTestCase() {
        
        String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            log.trace("No resource bundle found for: " + className);
        }
    }

    /**
     * 从资源文件中加载JavaBean属性的值
     * @param 要绑定的Java类型
     * @return 已绑定资源文件内值得Java对象
     * @throws Exception 复制对象出错后抛出异常
     */
    protected Object populate(final Object obj) throws Exception {
        // loop through all the beans methods and set its properties from its .properties file
        Map<String, String> map = new HashMap<String, String>();

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        BeanUtils.copyProperties(obj, map);

        return obj;
    }

    /**
     * 保存对象值后flush到数据库: http://issues.appfuse.org/browse/APF-178.
     *
     * @throws org.springframework.beans.BeansException
     *          找不到SessionFactory时抛出异常
     */
    protected void flush() throws BeansException {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.flush();
    }

    /**
     * reindex() 或 reindexAll() 操作后刷新查询索引
     */
    public void flushSearchIndexes() {
        Session currentSession = sessionFactory.getCurrentSession();
        final FullTextSession fullTextSession = Search.getFullTextSession(currentSession);
        fullTextSession.flushToIndexes();
    }
}
