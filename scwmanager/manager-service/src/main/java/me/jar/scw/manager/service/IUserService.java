package me.jar.scw.manager.service;

import me.jar.scw.manager.model.TUser;
import org.springframework.stereotype.Service;

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

    List<TUser> findAllUsers();

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

}
