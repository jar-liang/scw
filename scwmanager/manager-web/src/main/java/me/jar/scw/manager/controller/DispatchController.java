package me.jar.scw.manager.controller;

import me.jar.scw.manager.model.TRole;
import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.model.constant.Constants;
import me.jar.scw.manager.model.vo.PageVO;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IUserRoleService;
import me.jar.scw.manager.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description 转发页面
 * @Date 2020/3/8-10:35
 */
@Controller
public class DispatchController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

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
     *  转发许可维护页面
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("per/permission.do")
    public String showPermissionPage(HttpSession session, ModelMap modelMap) {
        setModelMap(session, modelMap, getUserNameFromSession(session));
        return "permission";
    }

    /**
     *  转发编辑页面
     */
    @RequestMapping("user/showedit.do")
    public String showEditPage(String id, HttpSession session, ModelMap modelMap) {
        String useName = getUserNameFromSession(session);
        if (useName != null) {
            // ModelMap将id放到页面上（不显示），编辑消息后取到id，根据id更新数据
            if (id == null || "".equals(id)) {
                return "redirect: /scw/user/list.do";
            }
            setModelMap(session, modelMap, useName);
            TUser userInfo = userService.getUserById(id);
            if (userInfo == null) {
                return "redirect: /scw/user/list.do";
            }
            modelMap.addAttribute("userId", id);
            modelMap.addAttribute("oldAccout", userInfo.getLoginAcct());
            modelMap.addAttribute("oldName", userInfo.getUserName());
            modelMap.addAttribute("oldEmail", userInfo.getEmail());
            return "edit";
        } else {
            return Constants.REDIRECT_TO_LOGIN;
        }
    }

    @RequestMapping("user/showassignrole.do")
    public String showAssignRole(String id, HttpSession session, ModelMap modelMap) {
        String useName = getUserNameFromSession(session);
        if (useName != null) {
            // ModelMap将id放到页面上（不显示），编辑消息后取到id，根据id更新数据
            if (id == null || "".equals(id)) {
                return "redirect: /scw/user/list.do";
            }
            Integer numId;
            try {
                numId = Integer.valueOf(id);
            } catch (NumberFormatException e) {
                System.out.println("invalid id");
                return "redirect: /scw/user/list.do";
            }
            Map<String, List<TRole>> partRoles = userRoleService.getPartRoles(numId);
            List<TRole> ownRoleList = partRoles.get("own");
            List<TRole> notOwnRoleList = partRoles.get("notOwn");
            setModelMap(session, modelMap, useName);
            modelMap.addAttribute("userId", id);
            modelMap.addAttribute("ownRoleList", ownRoleList);
            modelMap.addAttribute("notOwnRoleList", notOwnRoleList);
            return "assignRole";
        } else {
            return Constants.REDIRECT_TO_LOGIN;
        }
    }

    @RequestMapping("role/rolemanager.do")
    public String showRoleManagerPage(HttpSession session, ModelMap modelMap) {
        String useName = getUserNameFromSession(session);
        if (useName == null) {
            return Constants.REDIRECT_TO_LOGIN;
        }
        // 将菜单和登录用户名放进ModelMap中
        setModelMap(session, modelMap, useName);
        // 将查出的角色数据放进ModelMap中，查出前10个
        List<TRole> allRoles = userRoleService.findTenRoles();
        Integer roleAmount = userRoleService.getRoleAmount("");
        if (CollectionUtils.isEmpty(allRoles) || roleAmount == null) {
            modelMap.addAttribute("pageVO", null);
        } else {
            PageVO<TRole> pageVO = new PageVO<>(1, 10, roleAmount);
            pageVO.setList(allRoles);
            modelMap.addAttribute("pageVO", pageVO);
        }
        return "role";
    }

    /**
     *  登出系统
     * @return
     */
    @RequestMapping("logout.do")
    public String logout(HttpSession session) {
        Object user = session.getAttribute(Constants.USER_SESSION);
        if (user != null) {
            // 如果已登录，登出就删除该session
            session.removeAttribute(user.toString());
            System.out.println("用户【" + session.getAttribute(Constants.USER_NAME) + "】登出成功");
        }
        // 返回登录页面
        return Constants.REDIRECT_TO_LOGIN;
    }



    /**
     *  从session获取用户名，用来判断是否已经登录
     */
    public static String getUserNameFromSession(HttpSession session) {
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
    public static void setModelMap(HttpSession session, ModelMap modelMap, String userName) {
        userName = userName.substring(userName.lastIndexOf(':') + 1);
        modelMap.addAttribute("userName", userName);
        // 将菜单数据放进modelMap中
        List<PermissionVO> userMenu = (List<PermissionVO>) session.getAttribute(Constants.USER_MENU);
        modelMap.addAttribute("userMenu", userMenu);
    }
}
