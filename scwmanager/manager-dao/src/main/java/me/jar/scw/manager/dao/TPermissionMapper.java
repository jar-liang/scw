package me.jar.scw.manager.dao;

import me.jar.scw.manager.model.TPermission;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:22
 */
public interface TPermissionMapper {

    /**
     *  获取所有菜单
     * @return
     */
    List<TPermission> selectAllMenu();

    /**
     *  根据角色id查询对应的权限id
     * @param roleId
     * @return
     */
    List<Integer> selectPermissionIdByRoleId(String roleId);
}
