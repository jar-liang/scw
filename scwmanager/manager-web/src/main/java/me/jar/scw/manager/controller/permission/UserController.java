package me.jar.scw.manager.controller.permission;

import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.model.constant.Constants;
import me.jar.scw.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description 用户登录注册控制器
 * @Date 2020/2/27
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     *  处理用户注册
     * @param user
     * @return
     */
    @RequestMapping("reg.do")
    public String userReg(TUser user, ModelMap modelMap, HttpSession session) {
        //1.获取传过来的数据 TODO 校验
        //2.获取创建用户结果
        boolean result = userService.createNewUser(user);
        //3.返回响应结果给前端页面
        //成功，发送main页面过去，并且将用户名回显（同时创建session，存放用户Id拼接用户名)
        if (result) {
            session.setAttribute(Constants.USER_SESSION, user.getId() + ":" +  user.getUserName());
            return Constants.REDIRECT_TO_MAIN;
        }
        //失败，重定向到reg页面，并且携带已传入数据（后续考虑使用Ajax，不再重定向页面）
        modelMap.addAttribute("user", user);
        return "reg";
    }

    /**
     *  处理用户登录
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("login.do")
    public String userLogin(TUser user, ModelMap modelMap, HttpSession session) {
        //1.获取传过来的数据 TODO 校验
        //2.获取查询结果，成功返回用户在数据库的id，失败返回null
        Integer id = userService.checkLogin(user);
        //3.成功，将main页面发送过去，并将用户名回显（同时创建session，存放用户Id拼接用户名)
        if (id != null) {
            session.setAttribute(Constants.USER_SESSION, id + ":" +  user.getUserName());
            return Constants.REDIRECT_TO_MAIN;
        }
        //4.失败，重定向登录页面，并提示用户名或密码错误（后续考虑使用Ajax，不再重定向页面）
        modelMap.addAttribute("userName", user.getUserName());
        return "login";

    }
}
