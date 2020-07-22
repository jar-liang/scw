package me.jar.scw.manager.dao;

import me.jar.scw.manager.model.TUser;
import org.apache.ibatis.annotations.Param;

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
     *  查询用户信息
     *   添加条件查询
     * @return
     */
    List<TUser> selectAllUsers(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("search") String search);


    /**
     *  取到所有用户数量
     * @return
     */
    Integer countAllUsers(@Param("search") String search);

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

    /**
     *  根据id查询用户
     */
    TUser selectUserById(String userId);

    /**
     *  根据用户id更新用户数据
     * @param user
     * @return
     */
    Integer updateUserInfo(TUser user);

    /**
     *  根据id删除用户
     * @param idList
     * @return
     */
    Integer deleteUserById(List<Integer> idList);

    /**
     *  通过email查询用户id，也可以判断该邮箱是否有用户注册，如果返回结果为null，则该邮箱未注册用户
     * @param email
     * @return
     */
    Integer selectUserIdByEmail(String email);

    /**
     *  将用户id和对应的token存入token表
     * @param userId
     * @param token
     * @return
     */
    Integer insertUserToken(@Param("userId") Integer userId, @Param("token") String token);

    /**
     *  如果token表里有对应userId的记录，先删除该条记录
     * @param userId
     * @return
     */
    Integer deleteIfRecordExist(Integer userId);

    /**
     *  通过传来的token查询是否存在该token，并且时间要距离创建时间半小时之内
     * @param token
     * @return
     */
    Integer checkTokenExist(String token);

    /**
     *  通过token查找对应的userId
     *
     * @param token
     * @return
     */
    Integer selectUserIdByToken(String token);

    /**
     *  根据用户id更新对应的密码
     *
     * @param userId
     * @param pwd
     * @return
     */
    Integer updatePwdByUserId(@Param("userId") Integer userId, @Param("pwd") String pwd);
}
