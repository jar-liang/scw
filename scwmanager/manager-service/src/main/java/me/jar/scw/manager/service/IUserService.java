package me.jar.scw.manager.service;

import me.jar.scw.manager.model.TUser;

import java.util.List;

/**
 * @Description
 * @Date 2020/2/24-21:29
 */
public interface IUserService {
    /**
     *  创建新用户
     * @param user
     * @return
     */
    Integer createUser(TUser user);

    /**
     *  查询用户数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TUser> findAllUsers(Integer pageNum, Integer pageSize, String search);

    /**
     *  获取用户数量
     * @return
     */
    Integer getUserAmount(String search);

    /**
     *  用户注册，正确返回true，错误返回false（一般用户名重复会出现错误)
     * @param user
     * @return
     */
    boolean createNewUser(TUser user);

    /**
     *  用户登录验证，正确返回用户id，错误返回null
     * @param user
     * @return
     */
    Integer checkLogin(TUser user);

    /**
     *  根据id获取用户账户、名称、邮箱
     */
    TUser getUserById(String id);

    /**
     *  更新用户信息by 用户id
     * @return
     */
    Integer updateUserInfo(TUser user);

    /**
     *  删除用户
     * @param idList
     * @return
     */
    Integer deleteUser(List<Integer> idList);

    /**
     *  给用户邮件箱发送找回设置密码的邮件
     * @param email
     */
    void sendMailForResetPwd(String email);

    /**
     *  检查token有效性，重置用户密码
     *
     * @param token
     * @param pwd
     * @return
     */
    boolean resetPwd(String token, String pwd);

}
