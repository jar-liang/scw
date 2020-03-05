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

    boolean createNewUser(TUser user);

}
