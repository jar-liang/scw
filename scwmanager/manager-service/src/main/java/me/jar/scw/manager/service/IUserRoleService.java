package me.jar.scw.manager.service;

import me.jar.scw.manager.model.TRole;

import java.util.List;
import java.util.Map;

/**
 * 处理用户角色
 * @Date 2020/5/24-18:03
 */
public interface IUserRoleService {
    /**
     *  通过用户id获取对应的用户角色
     * @param id
     * @return
     */
    List<TRole> findUserRoleById(Integer id);

    /**
     *  查找所有的角色
     * @return
     */
    List<TRole> findAllRoles();

    /**
     *  获取id用户拥有和没拥有的权限
     * @param id
     * @return
     */
    Map<String, List<TRole>> getPartRoles(Integer id);
}
