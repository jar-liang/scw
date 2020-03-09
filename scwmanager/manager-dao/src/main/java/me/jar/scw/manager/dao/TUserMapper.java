package me.jar.scw.manager.dao;

import me.jar.scw.manager.model.TUser;

import java.util.List;

/**
 * @Description
 * @Date 2020/2/23-16:15
 */
public interface TUserMapper {
    /**
     *  插入用户数据
     * @param user
     * @return
     */
    Integer insertUser(TUser user);

    /**
     *  查询所有用户
     * @return
     */
    List<TUser> selectAllUsers();

    /**
     *  有选择地插入数据
     * @param user
     * @return
     */
    Integer insertUserSelective(TUser user);

    /**
     *  验证用户登录
     * @param user
     * @return
     */
    Integer checkUserLogin(TUser user);
}
