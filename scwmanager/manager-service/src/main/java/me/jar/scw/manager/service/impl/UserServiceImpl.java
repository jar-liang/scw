package me.jar.scw.manager.service.impl;

import me.jar.project.commons.EncryptUtils;
import me.jar.project.commons.StringUtils;
import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import me.jar.scw.manager.service.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer getUserAmount(String search) {
        try {
            return tUserMapper.countAllUsers(search);
        } catch (DataAccessException e) {
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
        } catch (DataAccessException e) {
            System.out.println("UserServiceImpl.checkLogin fail." + e.getMessage());
        }
        return null;
    }

    @Override
    public TUser getUserById(String id) {
        TUser user = null;
        try {
            user = tUserMapper.selectUserById(id);
        } catch (DataAccessException e) {
            System.out.println("get user info by id fail");
        }
        return user;
    }

    @Override
    public Integer updateUserInfo(TUser user) {
        Integer row = 0;
        try {
            row =  tUserMapper.updateUserInfo(user);
        } catch (DataAccessException e) {
            System.out.println("update fail");
        }
        return row;
    }

    @Override
    public Integer deleteUser(List<Integer> idList) {
        Integer row = 0;
        try {
            row = tUserMapper.deleteUserById(idList);
        } catch (DataAccessException e) {
            System.out.println("delete user fail");
        }
        return row;
    }

    @Override
    public void sendMailForResetPwd(String email) {
        try {
            // 查询数据库，是否存在该邮箱，存在则发送，否则不发送
            Integer userId = tUserMapper.selectUserIdByEmail(email);
            if (userId == null || userId == 0) {
                System.out.println("user does't exist");
                return;
            }
            // 有个问题待以后解决，如果用户很短时间内继续发找回密码，怎么处理（初步觉得可以查询token表里的时间
            // 如果时间过短，给出提示，过一定时间后再申请找回密码 TODO
            // 先删除已存在的记录
            tUserMapper.deleteIfRecordExist(userId);
            // 发送邮件，里面是一条重置密码的链接，链接带上token
            // 先生成token，UUID，将其与用户id对应(新建一个表，保存用户id和token对应关系，还有一个时效性，超时链接失效)
            String token = UUID.randomUUID().toString() + System.currentTimeMillis();
            Integer row = tUserMapper.insertUserToken(userId, token);
            if (Integer.valueOf(0).equals(row)) {
                System.out.println("insert token fail");
                return;
            }
            // 发送邮件 1.先生成带token的url 2.将该url发送到用户注册邮箱
            // 该url要考虑controller的接口 TODO
            String urlForRestPwd = "http://127.0.0.1:8080/scw/user/resetpwdpage.do?token=" +token;
            ServiceUtils.sendEmailForFindPwd(email,urlForRestPwd);
            System.out.println("email send");
        } catch (DataAccessException e) {
            System.out.println("select userid fail or insert token failed");
            e.printStackTrace();
        }
    }

    private static String getEncryptPwd(String pwd) {
        if (pwd == null || "".equals(pwd)) {
            return null;
        }
        return EncryptUtils.getSHACode(pwd, "scw", "SHA-256");
    }
}
