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

    // 删除角色结果，1代表成功，2代表由于外键约束删除失败
    private final Integer DELETE_SUCCESS = 1;
    private final Integer DELETE_FAIL_RESTRAIN = 2;

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
        List<String> pIdList = checkMultiId(permissionId);
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
        return result.toJSONString();
    }

    /**
     *  删除该角色的所有权限
     * @param roleId
     * @return
     */
    @RequestMapping("deleteallpermission.do")
    @ResponseBody
    public String deleteAllPermission(String roleId) {
        String roleIdParam = ControllerUtils.checkId(roleId);
        JSONObject result = new JSONObject();
        Integer row = permissionService.deleteAllPermission(roleIdParam);
        if (row != null) {
            result.put(Constants.STATUS, Constants.SUCCESS);
        } else {
            result.put(Constants.STATUS, Constants.FAIL);
        }
        return result.toJSONString();
    }

    /**
     *  删除角色，批量与单个都可以删除
     * @param roleId
     * @return
     */
    @RequestMapping("delrole.do")
    @ResponseBody
    public String deleteRole(String roleId) {
        List<String> roleIdParam = checkMultiId(roleId);
        JSONObject result = new JSONObject();
        Integer deleteResult = permissionService.deleteRoleById(roleIdParam);
        if (DELETE_SUCCESS.equals(deleteResult)) {
            result.put(Constants.STATUS, Constants.SUCCESS);
        } else if (DELETE_FAIL_RESTRAIN.equals(deleteResult)) {
            result.put(Constants.STATUS, "restrain");
        } else {
            result.put(Constants.STATUS, Constants.FAIL);
        }
        return result.toJSONString();
    }

    /**
     *  对permissionId做校验
     * @param multiId
     * @return
     */
    private List<String> checkMultiId(String multiId) {
        List<String> multiIdList = new ArrayList<>();
        String checkPid = ControllerUtils.checkString(multiId, ControllerUtils.PARAM_LENGTH_MAX_256);
        String[] pIds = checkPid.split(",");
        if (pIds != null && pIds.length >= 1) {
            for (String pid : pIds) {
                if (!StringUtils.EMPTY.equals(pid) && !StringUtils.EMPTY.equals(ControllerUtils.checkId(pid))) {
                    multiIdList.add(pid);
                }
            }
        }
        return multiIdList;
    }

}
