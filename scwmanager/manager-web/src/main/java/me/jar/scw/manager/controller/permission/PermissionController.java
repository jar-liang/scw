package me.jar.scw.manager.controller.permission;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.jar.scw.manager.controller.constant.Constants;
import me.jar.scw.manager.controller.util.ControllerUtils;
import me.jar.scw.manager.model.TPermission;
import me.jar.scw.manager.service.IPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 权限控制器类
 * @Date 2020/6/7-11:50
 */
@Controller
@RequestMapping("permission")
@Transactional
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     *  获取权限树
     * @return
     */
    @RequestMapping("permissiontree.do")
    @ResponseBody
    public String getPermissionTree() {
        List<TPermission> permission = permissionService.findPermission();
        if (CollectionUtils.isEmpty(permission)) {
            return "";
        }
        return JSON.toJSONString(permission);
    }

    /**
     *  根据角色id查找对应的权限id，使权限树将该角色对应的权限勾上
     * @param id
     * @return
     */
    @RequestMapping("permissionid.do")
    @ResponseBody
    public String getPermissionIdByRoleId(String id) {
        String idParam = ControllerUtils.checkId(id);
        List<Integer> permissionIds = permissionService.findPermissionIdByRoleId(idParam);
        permissionIds = permissionIds != null ? permissionIds : new ArrayList<>();
        return JSON.toJSONString(permissionIds);
    }

    /**
     *  更新角色权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @RequestMapping("updatepermission.do")
    @ResponseBody
    public String updatePermission(String roleId, String permissionId) {
        String roleIdParam = ControllerUtils.checkId(roleId);
        List<String> pIdList = checkPermissionId(permissionId);
        JSONObject result = new JSONObject();
        boolean flag = false;
        try {
            flag = permissionService.updateRolePermission(roleIdParam, pIdList);
        } catch (DataAccessException e) {
            System.out.println("execute update fail");
        }
        if (flag) {
            result.put(Constants.STATUS, Constants.SUCCESS);
        } else {
            result.put(Constants.STATUS, Constants.FAIL);
        }
        return JSON.toJSONString(result);
    }

    /**
     *  对permissionId做校验
     * @param permissionId
     * @return
     */
    private List<String> checkPermissionId(String permissionId) {
        List<String> permissionIdList = new ArrayList<>();
        String checkPid = ControllerUtils.checkString(permissionId, ControllerUtils.PARAM_LENGTH_MAX_256);
        String[] pIds = checkPid.split(",");
        if (pIds != null && pIds.length >= 1) {
            for (String pid : pIds) {
                if (!StringUtils.EMPTY.equals(pid) && !StringUtils.EMPTY.equals(ControllerUtils.checkId(pid))) {
                    permissionIdList.add(pid);
                }
            }
        }
        return permissionIdList;
    }

}
