package me.jar.scw.manager.dao;

import me.jar.scw.manager.model.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Date 2020/2/23-16:15
 */
@Repository
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
}
