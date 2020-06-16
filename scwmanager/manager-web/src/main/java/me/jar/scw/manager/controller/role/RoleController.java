package me.jar.scw.manager.controller.role;

import com.alibaba.fastjson.JSONObject;
import me.jar.scw.manager.model.TRole;
import me.jar.scw.manager.model.constant.Constants;
import me.jar.scw.manager.model.vo.PageVO;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IUserRoleService;
import org.apache.commons.collections.CollectionUtils;
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
 * @Description 用户角色处理控制器
 * @Date 2020/5/26-22:38
 */
@Controller
@RequestMapping("role")
public class RoleController {

    // controller处理状态
    private static final String STATUS = "status";
    // 处理成功
    private static final String SUCCESS = "success";
    // 处理失败
    private static final String FAIL = "fail";

    @Autowired
    IUserRoleService userRoleService;

    @RequestMapping("addrole.do")
    @ResponseBody
    public String addRole(String ids, String userId) {
        JSONObject result = new JSONObject();
        // 处理ids成 List<Integer>
        List<Integer> roleIdList = getRoleIdList(ids);
        if (roleIdList == null || roleIdList.isEmpty()) {
            result.put(STATUS, FAIL);
            return result.toJSONString();
        }
        // userId转成Integer
        try {
            Integer userIdNum = Integer.valueOf(userId);
            Integer row = userRoleService.addRoles(userIdNum, roleIdList);
            if (row != null && row > 0) {
                result.put(STATUS, SUCCESS);
            } else {
                result.put(STATUS, FAIL);
            }
        } catch (NumberFormatException e) {
            System.out.println("invalid userId");
            result.put(STATUS, FAIL);
        }
        return result.toJSONString();
    }

    /**
     *  将角色id拆分并转换成Integer
     * @param ids
     * @return
     */
    private List<Integer> getRoleIdList(String ids) {
        List<Integer> roleIdList = new ArrayList<>();
        try {
            if (ids.contains(",")) {
                String[] splitId = ids.split(",");
                for (String idStr : splitId) {
                    roleIdList.add(Integer.valueOf(idStr));
                }
            } else {
                roleIdList.add(Integer.valueOf(ids));
            }
            return roleIdList;
        } catch (NumberFormatException e) {
            System.out.println("invalid ids");
        }
        return null;
    }

    @RequestMapping("deluserrole.do")
    @ResponseBody
    public String deleteRole(String ids, String userId) {
        JSONObject result = new JSONObject();
        // 处理ids 和 userId，转为合法Integer
        List<Integer> roleIdList = getRoleIdList(ids);
        if (CollectionUtils.isEmpty(roleIdList)) {
            result.put(STATUS, FAIL);
            return result.toJSONString();
        }
        try {
            Integer userIdNum = Integer.valueOf(userId);
            Integer row = userRoleService.deleteUserRole(userIdNum, roleIdList);
            if (row != null && row >= 0) {
                result.put(STATUS, SUCCESS);
            } else {
                result.put(STATUS, FAIL);
            }
        } catch (NumberFormatException e) {
            System.out.println("invalid userId");
            result.put(STATUS, FAIL);
        }
        return result.toJSONString();
    }

    /**
     *  查出所有角色
     * @param pageNum
     * @param pageListSize
     * @param search
     * @param modelMap
     * @param session
     * @return
     */
    @RequestMapping("rolelist.do")
    public String showRoleList(@RequestParam(value = "num", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "size", defaultValue = "10")Integer pageListSize,
                               @RequestParam(value = "search", required = false) String search,
                               ModelMap modelMap, HttpSession session) {
        //3.把菜单页放入modelMap中
        List<PermissionVO> userMenu = (List<PermissionVO>) session.getAttribute(Constants.USER_MENU);
        modelMap.addAttribute("userMenu", userMenu);
        // 回显search的内容
        if (!StringUtils.isEmpty(search)) {
            modelMap.addAttribute("showSearch", search);
        }
        // 做pageNum和pageListSize的校验，非空，非负数 TODO
        if (pageNum == null || pageListSize == null || pageNum < 0 || pageListSize < 0) {
            modelMap.addAttribute("pageVO", null);
            return "role";
        }
        Integer roleAmount = userRoleService.getRoleAmount(search);
        if (roleAmount == null || roleAmount == 0) {
            modelMap.addAttribute("pageVO", null);
        } else {
            // 通过页数和关键字查找角色数据
            List<TRole> userRole = userRoleService.findUserRoleByKeyWord(pageNum, pageListSize, search);
            // 分页
            PageVO<TRole> pageVO = new PageVO<>(pageNum, pageListSize, roleAmount);
            pageVO.setList(userRole);
            modelMap.addAttribute("pageVO", pageVO);
        }
        return "role";
    }

    @RequestMapping("addRoleName.do")
    @ResponseBody
    public String addRoleName(String roleName) {
        JSONObject result = new JSONObject();
        // 取到角色名，先判断是否已存在，存在则返回提示，提示status为duplicate
        if (userRoleService.checkRoleNameExist(roleName)) {
            result.put(STATUS, "duplicate");
            return result.toJSONString();
        }
        // 角色名对应id，查到的最大的id的基础上+1
        if (userRoleService.addNewRoleByName(roleName)) {
            result.put(STATUS, SUCCESS);
        } else {
            result.put(STATUS, FAIL);
        }
        return result.toJSONString();
    }
}
