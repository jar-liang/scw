package me.jar.scw.manager.model.constant;

/**
 * @Description
 * @Date 2020/3/5-0:26
 */
public interface Constants {
    /**
     *  session中存放id:userName的key
     */
    String USER_SESSION = "user";

    /**
     *  重定向到登录页面
     */
    String REDIRECT_TO_LOGIN = "redirect: /scw/login.html";

    /**
     *  注册或登录成功重定向到控制台页面
     */
    String REDIRECT_TO_MAIN = "redirect: /scw/main.do";

    /**
     *  session中保存用户菜单的key
     */
    String USER_MENU = "userMenu";

    /**
     *  session中放置用户名称，方便thymeleaf拿取
     */
    String USER_NAME = "userName";

}
