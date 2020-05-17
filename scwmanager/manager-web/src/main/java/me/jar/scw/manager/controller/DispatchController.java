package me.jar.scw.manager.controller;

import me.jar.scw.manager.model.constant.Constants;
import me.jar.scw.manager.model.vo.PermissionVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 转发页面
 * @Date 2020/3/8-10:35
 */
@Controller
public class DispatchController {
    /**
     *  转发主页面
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("main.do")
    public String toMain(HttpSession session, ModelMap modelMap) {
        String userName = getUserNameFromSession(session);
        if (userName == null) {
            return Constants.REDIRECT_TO_LOGIN;
        } else {
            setModelMap(session, modelMap, userName);
            return "main";
        }
    }

    /**
     *  转发新增页面
     */
    @RequestMapping("user/add.do")
    public String addUserPage(HttpSession session, ModelMap modelMap) {
        String useName = getUserNameFromSession(session);
        if (useName != null) {
            setModelMap(session, modelMap, useName);
            return "add";
        } else {
            return Constants.REDIRECT_TO_LOGIN;
        }
    }

    /**
     *  从session获取用户名，用来判断是否已经登录
     */
    private String getUserNameFromSession(HttpSession session) {
        Object userName = session.getAttribute(Constants.USER_SESSION);
        if (userName != null) {
            return (String)userName;
        } else {
            return null;
        }
    }

    /**
     *  将用户名数据放入ModelMap
     */
    private void setModelMap(HttpSession session, ModelMap modelMap, String userName) {
        userName = userName.substring(userName.lastIndexOf(':') + 1);
        modelMap.addAttribute("userName", userName);
        // 将菜单数据放进modelMap中 TODO
        List<PermissionVO> userMenu = (List<PermissionVO>) session.getAttribute(Constants.USER_MENU);
        modelMap.addAttribute("userMenu", userMenu);
    }
}
