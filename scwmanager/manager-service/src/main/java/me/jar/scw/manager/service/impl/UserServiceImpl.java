package me.jar.scw.manager.service.impl;

import me.jar.project.commons.EncryptUtils;
import me.jar.project.commons.StringUtils;
import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TUser> findAllUsers(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        try {
            List<TUser> users = tUserMapper.selectAllUsers(pageNum, pageSize);
            if (users.isEmpty()) {
                return null;
            }
            return users;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer getUserAmount() {
        try {
            Integer num = tUserMapper.countAllUsers();
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
        } catch (Exception e) {
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

    private static String getEncryptPwd(String pwd) {
        if (pwd == null || "".equals(pwd)) {
            return null;
        }
        return EncryptUtils.getSHACode(pwd, "scw", "SHA-256");
    }
}
