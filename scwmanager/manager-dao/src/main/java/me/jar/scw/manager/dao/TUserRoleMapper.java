package me.jar.scw.manager.dao;

import me.jar.scw.manager.model.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色mapper层
 * @Date 2020/5/24-17:50
 */
public interface TUserRoleMapper {
    List<TRole> selectRoleByUserId(Integer id);

    List<TRole> selectAllRole();

    Integer insertRoleByUserId(@Param("userId") Integer userId, @Param("roleIdList") List<Integer> roleIdList);

    Integer deleteRoleIdUserId(@Param("userId") Integer userId, @Param("roleIdList") List<Integer> roleIdList);

    List<TRole> selectRoleByKeyWord(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,
                                    @Param("keyWord") String keyWord);

    Integer countRoleNumByKeyWord(@Param("keyWord") String keyWord);

    List<TRole> selectTenRole();
}
