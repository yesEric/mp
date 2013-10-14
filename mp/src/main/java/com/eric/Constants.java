package com.eric;


/**
 * 应用程序使用到的静态变量.
 *
 */
public final class Constants {

    private Constants() {
        // hide me
    }
    //~ 静态变量和初始值 =============================================

    /**
     * 应用程序中ResourceBundle的名称
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * 
     * 系统属性中的文件分割方式
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * 系统属性中的用户主目录
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * 应用程序中存储的配置文件名称.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     * 
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     * @deprecated No longer used to set themes.
     */
    public static final String CSS_THEME = "csstheme";
}
