package me.jar.scw.manager.dao;

import me.jar.scw.manager.model.TRole;

import java.util.List;

/**
 * 用户角色mapper层
 * @Date 2020/5/24-17:50
 */
public interface TUserRoleMapper {
    List<TRole> selectRoleByUserId(Integer id);

}
