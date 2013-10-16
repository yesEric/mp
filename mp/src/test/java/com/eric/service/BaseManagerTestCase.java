package com.eric.service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.eric.util.ConvertUtil;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@ContextConfiguration(locations = {
        "classpath:/applicationContext-resources.xml", "classpath:/applicationContext-dao.xml",
        "classpath:/applicationContext-service.xml", "classpath*:/**/applicationContext.xml"
})
/**
 *
 * 所有的测试类都可以继承这个基类，它基于Spring的上下文获取需要的配置信息。
 */
public abstract class BaseManagerTestCase extends AbstractTransactionalJUnit4SpringContextTests {

    /**
     * 声明日志对象
     */
    protected final Log log = LogFactory.getLog(getClass());
    /**
     * 资源绑定对象
     */
    protected ResourceBundle rb;

    /**
     * 如果需要在构造函数中初始化资源绑定对象.
     */
    public BaseManagerTestCase() {
        // 并不是所有的类都有资源文件，这里做一下检查.
        String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            // log.warn("No resource bundle found for: " + className);
        }
    }

    /**
     * 通过资源文件装载对象的属性值.
     *
     * @param obj 需要装载属性值得对象
     * @return 根据资源文件装载属性值的对象
     * @throws Exception BeanUtils拷贝属性失败是抛出的异常
     */
    protected Object populate(Object obj) throws Exception {
        // loop through all the beans methods and set its properties from
        // its .properties file
        Map map = ConvertUtil.convertBundleToMap(rb);

        BeanUtils.copyProperties(obj, map);

        return obj;
    }
}
