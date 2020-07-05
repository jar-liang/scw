package me.jar.scw.manager.service;

import me.jar.scw.manager.model.TPermission;
import me.jar.scw.manager.model.vo.PermissionVO;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:37
 */
public interface IPermissionService {

    /**
     *  找出所有菜单，分好子父级
     * @return
     */
    List<PermissionVO> findAllMemu();

    /**
     *  根据用户id找出对应权限菜单
     * @param userId
     * @return
     */
    List<PermissionVO> findMenuByUserId(Integer userId);

    /**
     *  找出所有菜单
     * @return
     */
    List<TPermission> findPermission();

    /**
     *  根据角色id查询出对应权限id
     * @param roleId
     * @return
     */
    List<Integer> findPermissionIdByRoleId(String roleId);

    /**
     *  更新角色对应的权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean updateRolePermission(String roleId, List<String> permissionIds);

    /**
     *  根据角色id删除角色
     * @param roleIdList
     * @return 返回1，删除成功，返回2，由于外键约束，无法删除成功，返回3，其它原因删除失败
     */
    Integer deleteRoleById(List<String> roleIdList);

    /**
     *  根据角色id删除它 所有权限
     * @param roleId
     * @return
     */
    Integer deleteAllPermission(String roleId);
}
