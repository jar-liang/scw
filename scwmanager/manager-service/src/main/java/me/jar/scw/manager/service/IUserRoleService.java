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

    /**
     *  给用户添加新角色
     * @param userId
     * @param roleIdList
     * @return
     */
    Integer addRoles(Integer userId, List<Integer> roleIdList);

    /**
     *  根据用户id和角色id删除对应的数据，即移除用户角色
     * @param userId
     * @param roleIdList
     * @return
     */
    Integer deleteUserRole(Integer userId, List<Integer> roleIdList);

    /**
     *  通过分页，关键字查询角色数据
     * @param pageNum
     * @param pageListSize
     * @param search
     * @return
     */
    List<TRole> findUserRoleByKeyWord(Integer pageNum, Integer pageListSize, String search);

    /**
     *  查找关键字查询角色数量
     * @param search
     * @return
     */
    Integer getRoleAmount(String search);
}
