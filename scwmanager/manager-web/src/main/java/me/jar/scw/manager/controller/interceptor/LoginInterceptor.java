package me.jar.scw.manager.controller.interceptor;

import me.jar.scw.manager.model.constant.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 拦截器，需要登录才能进行操作
 * @Date 2020/6/23-22:13
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object userName = httpServletRequest.getSession().getAttribute(Constants.USER_SESSION);
        if (userName == null || "".equals(userName.toString())) {
            System.out.println("拦截器，请求不合理，不给通过");
            httpServletResponse.sendRedirect("/scw/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
