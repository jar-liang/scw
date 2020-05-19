package me.jar.scw.manager.service.impl;

import me.jar.project.commons.EncryptUtils;
import me.jar.project.commons.StringUtils;
import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Date 2020/2/24-21:31
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public Integer createUser(TUser user) {
        String userPswd = user.getUserPswd();
        userPswd = EncryptUtils.getSHACode(userPswd, "scw", "SHA-256");
        user.setUserPswd(userPswd);
        return tUserMapper.insertUser(user);
    }

    @Override
    public List<TUser> findAllUsers(Integer pageNum, Integer pageSize, String search) {
        pageNum = (pageNum - 1) * pageSize;
        try {
            List<TUser> users = tUserMapper.selectAllUsers(pageNum, pageSize, search);
            if (users.isEmpty()) {
                return null;
            }
            return users;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer getUserAmount(String search) {
        try {
            Integer num = tUserMapper.countAllUsers(search);
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean createNewUser(TUser user) {
        user.setLoginAcct(user.getUserName());
        user.setUserPswd(getEncryptPwd(user.getUserPswd()));
        user.setCreateTime(StringUtils.timeToString(new Date()));
        try {
            Integer row = tUserMapper.insertUserSelective(user);
            if (row == 1) {
                return true;
            }
        } catch (DataAccessException e) {
            System.out.println("UserServiceImpl.createNewUser fail" + e.getMessage());
        }
        return false;
    }

    /**
     *  验证登录信息是否正确，正确返回用户id
     * @param user
     * @return
     */
    @Override
    public Integer checkLogin(TUser user) {
        user.setUserPswd(getEncryptPwd(user.getUserPswd()));
        try {
            Integer id = tUserMapper.checkUserLogin(user);
            if (id != null) {
                return id;
            }
        } catch (Exception e) {
            System.out.println("UserServiceImpl.checkLogin fail." + e.getMessage());
        }
        return null;
    }

    @Override
    public TUser getUserById(String id) {
        TUser user = tUserMapper.selectUserById(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    private static String getEncryptPwd(String pwd) {
        if (pwd == null || "".equals(pwd)) {
            return null;
        }
        return EncryptUtils.getSHACode(pwd, "scw", "SHA-256");
    }
}
