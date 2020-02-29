package me.jar.scw.manager.controller;

import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String userReg(TUser user, ModelMap modelMap) {
        System.out.println("UserController.userReg()");
        Integer row = userService.createUser(user);
        if (row == 1) {
            modelMap.addAttribute("userName", user.getUserName());
            return "main";
        } else {
            return "index2";
        }
    }
}
