package me.jar.scw.manager.controller;

import me.jar.scw.manager.model.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description 转发页面
 * @Date 2020/3/8-10:35
 */
@Controller
public class DispatchController {
    @RequestMapping("main.do")
    public String toMain(HttpSession session, ModelMap modelMap) {
        System.out.println("main.do");
        if (session.getAttribute(Constants.USER_SESSION) == null) {
            return Constants.REDIRECT_TO_LOGIN;
        } else {
            String userIdAndName = (String) session.getAttribute(Constants.USER_SESSION);
            String userName = userIdAndName.substring(userIdAndName.lastIndexOf(':') + 1);
            modelMap.addAttribute("userName", userName);
            // 将菜单数据放进modelMap中 TODO

            return "main";
        }
    }
}
