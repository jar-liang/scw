package me.jar.scw.manager.service;

import me.jar.scw.manager.model.TRole;

import java.util.List;

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
}
