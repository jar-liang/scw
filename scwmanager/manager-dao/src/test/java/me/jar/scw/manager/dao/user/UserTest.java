package me.jar.scw.manager.dao.user;

import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Date 2020/3/4-0:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
public class UserTest {
    @Autowired
    TUserMapper userMapper;

    @Test
    public void testInsertUserSelective() {
        TUser user = new TUser();
        user.setUserName("Tom3");
        user.setUserPswd("bbbb");
        user.setLoginAcct("aaa");
        user.setEmail("bbb");
//        user.setCreateTime("dddd");
        Integer row = userMapper.insertUserSelective(user);
        System.out.println("testInsertUserSelective effective: " + row);
        System.out.println("user id: " + user.getId());
    }

    @Test
    public void testCheckUserLogin() {
        TUser user = new TUser();
        user.setUserName("李四");
        user.setUserPswd("ef67d9f509f77382d7bbd4e4844a3dc4121f4b294a28941cd521b098b8aabdc");
        Integer id = userMapper.checkUserLogin(user);
        System.out.println("row is " + id);
    }

    @Test
    public void testSelectAllUser() {
        List<TUser> users = userMapper.selectAllUsers(1, 5, "sg");
        for (TUser user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testCountAllUsers() {
        Integer count = userMapper.countAllUsers("sg");
        System.out.println(count);
    }

    @Test
    public void testSelectUserById() {
        TUser user = userMapper.selectUserById("45");
        System.out.println(user);
    }

    @Test
    public void testUpdateUserInfo() {
        TUser user = new TUser();
        user.setId(58);
        user.setLoginAcct("7777");
        user.setUserName("7777");
        user.setEmail("7777@77.com");
        Integer row = userMapper.updateUserInfo(user);
        if (row == 1) {
            System.out.println("success");
        }
    }

    @Test
    public void testDeleteUserById() {
        List<Integer> idList = Arrays.asList(93,96);
        Integer row = userMapper.deleteUserById(idList);
        System.out.println("delete number: " + row);
    }

    @Test
    public void testSelectUserIdByEmail() {
        String email = "aaa@bbb.bom";
        Integer id = userMapper.selectUserIdByEmail(email);
        if (id != null) {
            System.out.println("user id is " + id);
        } else {
            System.out.println("user not exist");
        }
    }

    @Test
    public void testInsertUserToken() {
        String token = UUID.randomUUID().toString() + System.currentTimeMillis();
        Integer userId = 69;
        Integer row = userMapper.insertUserToken(userId, token);
        if (Integer.valueOf(1).equals(row)) {
            System.out.println("insert success");
        } else {
            System.out.println("insert fail");
        }
    }

    @Test
    public void testDeleteIfRecordExist() {
        Integer userId = 45;
        Integer row = userMapper.deleteIfRecordExist(userId);
        System.out.println(row);
    }

    @Test
    public void testCheckTokenExist() {
        String token = "7cd4e4e3-c72a-466e-8213-bbe04113b92b1595260950656";
        Integer result = userMapper.checkTokenExist(token);
        if (result != null && result == 1) {
            System.out.println("token valid");
        } else {
            System.out.println("invalid token");
        }
    }

    @Test
    public void testSelectUserIdByToken() {
        String token = "7cd4e4e3-c72a-466e-8213-bbe04113b92b1595260950656";
        Integer userId = userMapper.selectUserIdByToken(token);
        System.out.println(userId);
    }

    @Test
    public void testUpdatePwdByUserId() {
        Integer userId = 64;
        String pwd = "bbbbbb";
        Integer row = userMapper.updatePwdByUserId(userId, pwd);
        System.out.println(row);
    }
}
