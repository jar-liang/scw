package me.jar.scw.manager.controller;

import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 用户登录注册控制器
 * @Date 2020/2/27-23:05
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
//    @ResponseBody
    public String userReg(TUser user) {
        System.out.println("UserController.userReg()");
        Integer row = userService.createUser(user);
        if (row == 1) {
//            return "{\"state\":\"success\"}";
            return "success";
        } else {
//            return "{\"state\":\"fail\"}";
            return "index2";
        }
    }
}
