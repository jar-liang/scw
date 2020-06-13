package me.jar.scw.manager.controller.permission;

import com.alibaba.fastjson.JSON;
import me.jar.scw.manager.controller.util.ControllerUtils;
import me.jar.scw.manager.model.TPermission;
import me.jar.scw.manager.service.IPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 权限控制器类
 * @Date 2020/6/7-11:50
 */
@Controller
@RequestMapping("permission")
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
        return JSON.toJSONString(permissionIds);
    }

}
