package me.jar.scw.manager.controller.role;

import com.alibaba.fastjson.JSONObject;
import me.jar.scw.manager.service.IUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("delrole.do")
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


}
