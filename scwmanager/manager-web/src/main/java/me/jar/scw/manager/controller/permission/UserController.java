package me.jar.scw.manager.controller.permission;

import com.alibaba.fastjson.JSONObject;
import me.jar.scw.manager.controller.DispatchController;
import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.model.constant.Constants;
import me.jar.scw.manager.model.vo.PageVO;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IPermissionService;
import me.jar.scw.manager.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用户登录注册控制器
 * @Date 2020/2/27
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    /**
     *  查出所有用户，返回前端
     * @return
     */
    @RequestMapping("list.do")
    public String showUsers(@RequestParam(value = "num", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "size", defaultValue = "10")Integer pageListSize,
                            @RequestParam(value = "search", required = false) String search,
                            ModelMap modelMap, HttpSession session) {
        //1.拿到数据库数据  TODO 参数校验，如果userAmount为0的情况
        //3.把菜单页放入modelMap中
        List<PermissionVO> userMenu = (List<PermissionVO>) session.getAttribute(Constants.USER_MENU);
        modelMap.addAttribute("userMenu", userMenu);
        // 回显search的内容
        if (!StringUtils.isEmpty(search)) {
            modelMap.addAttribute("showSearch", search);
        }
        Integer userAmount = userService.getUserAmount(search);
        if (userAmount == 0) {
            modelMap.addAttribute("pageVO", null);
            return "user";
        }
        List<TUser> users = userService.findAllUsers(pageNum, pageListSize, search);

        //2.分页
        PageVO<TUser> pageVO = new PageVO<>(pageNum, pageListSize, userAmount);
        pageVO.setList(users);
        modelMap.addAttribute("pageVO", pageVO);
        return "user";
    }

    /**
     *  处理用户注册
     * @param user
     * @return
     */
    @RequestMapping("reg.do")
    @ResponseBody
    public String userReg(TUser user) {
        JSONObject result = new JSONObject();
        boolean isSuccess = userService.createNewUser(user);
        if (isSuccess) {
            result.put(me.jar.scw.manager.controller.constant.Constants.STATUS,
                    me.jar.scw.manager.controller.constant.Constants.SUCCESS);
        } else {
            result.put(me.jar.scw.manager.controller.constant.Constants.STATUS,
                    me.jar.scw.manager.controller.constant.Constants.FAIL);
        }
        return result.toJSONString();
    }

    /**
     *  处理用户登录
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("login.do")
    @ResponseBody
    public String userLogin(TUser user, HttpSession session) {
        JSONObject result = new JSONObject();
        //1.获取传过来的数据 TODO 校验
        //2.获取查询结果，成功返回用户在数据库的id，失败返回null
        Integer id = userService.checkLogin(user);
        //3.成功，将main页面发送过去，并将用户名回显（同时创建session，存放用户Id拼接用户名)
        if (id != null) {
            session.setAttribute(Constants.USER_SESSION, id + ":" +  user.getUserName());
            addUserName(session, user.getUserName());
            addUserMenuByUserId(session, id);
            System.out.println("用户【" + user.getUserName() + "】登录成功");
            result.put(me.jar.scw.manager.controller.constant.Constants.STATUS,
                me.jar.scw.manager.controller.constant.Constants.SUCCESS);
            return result.toJSONString();
        }
        //4.失败，重定向登录页面，并提示用户名或密码错误（后续考虑使用Ajax，不再重定向页面）
        result.put(me.jar.scw.manager.controller.constant.Constants.STATUS,
                me.jar.scw.manager.controller.constant.Constants.FAIL);
        return result.toJSONString();
    }

    /**
     *  新增用户
     */
    @RequestMapping("adduser.do")
    public String addNewUser(TUser user) {
        // 调用userService.createNewUser(user)创建用户，根据结果做判断
        boolean result = userService.createNewUser(user);
        // 成功，返回用户列表页面，使用重定向，直接访问list.do
        if (result) {
            return "redirect: list.do";
        } else {
            // 失败，提示，目前用继续转发新增页面
            return "redirect: add.do";
        }
    }

    /**
     *  编辑用户信息
     */
    @RequestMapping("edituser.do")
    @ResponseBody
    public String editUserInfo(TUser user, HttpSession session) {
        String useName = DispatchController.getUserNameFromSession(session);
        JSONObject result = new JSONObject();
        if (useName == null) {
            result.put("status", "no login");
            return result.toJSONString();
        }
        Integer row = userService.updateUserInfo(user);
        if (row == 1) {
            result.put("status", "success");
        } else {
            System.out.println("update user info fail");
            result.put("status", "fail");
        }
        return result.toJSONString();
    }

    /**
     *  删除用户
     */
    @RequestMapping("deleteuser.do")
    @ResponseBody
    public String deleteUser(String id, HttpSession session) {
        String useName = DispatchController.getUserNameFromSession(session);
        JSONObject result = new JSONObject();
        if (useName == null) {
            result.put("status", "no login");
            return result.toJSONString();
        }
        // 处理删除业务
        List<Integer> idList = getIdList(id);
        if (idList == null || idList.isEmpty()) {
            result.put("status", "fail");
            return result.toJSONString();
        }
        Integer row = userService.deleteUser(idList);
        if (row > 0) {
            result.put("status", "success");
            return result.toJSONString();
        } else {
            result.put("status", "fail");
            return result.toJSONString();
        }
    }

    /**
     *  将前端返回的id进行拆分，分别放入list中
     * @param id
     * @return
     */
    private List<Integer> getIdList(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        List<Integer> ids = new ArrayList<>();
        try {
            if (id.contains(",")) {
                String[] splitId = id.split(",");
                for (String part : splitId) {
                    ids.add(Integer.valueOf(part));
                }
            } else {
                ids.add(Integer.valueOf(id));
            }
        } catch (NumberFormatException e) {
            System.out.println("invalid format number");
            ids = null;
        }
        return ids;
    }

    /**
     *  登录成功将菜单放入session中
     * @param session
     */
    private void addUserMenuByUserId(HttpSession session, Integer userId) {
        List<PermissionVO> allMemu = permissionService.findMenuByUserId(userId);
        session.setAttribute(Constants.USER_MENU, allMemu);
    }


    /**
     *  session中放入用户，方便nav读取
     * @param session
     * @param userName
     */
    private void addUserName(HttpSession session, String userName) {
        session.setAttribute(Constants.USER_NAME, userName);
    }
}
