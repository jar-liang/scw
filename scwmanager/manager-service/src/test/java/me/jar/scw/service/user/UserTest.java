package me.jar.scw.service.user;

import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import me.jar.scw.manager.service.util.ServiceUtils;
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
 * @Date 2020/3/1-9:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
public class UserTest {
    @Autowired
    IUserService userService;

    @Test
    public void testFindUsers() {
        List<TUser> users = userService.findAllUsers(1, 5, "sg");
        for (TUser user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserAmount() {
        Integer num = userService.getUserAmount("sg");
        System.out.println(num);
    }


    @Test
    public void testCreateNewUser() {
        TUser user = new TUser();
        user.setUserName("我顶");
        user.setUserPswd("123456");
        user.setEmail("12@33.com");
        boolean result = userService.createNewUser(user);
        System.out.println(result);
        System.out.println("id: " + user.getId());
    }

    @Test
    public void testCheckLogin() {
        TUser user = new TUser();
        user.setUserName("Jerry");
        user.setUserPswd("123456");
        Integer id = userService.checkLogin(user);
        if (id != null) {
            System.out.println("login success! user id is " + id);
        } else {
            System.out.println("login fail!");
        }
    }

    @Test
    public void testGetUserById() {
        TUser user = userService.getUserById("500");
        if (user != null) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUserInfo() {
        TUser user = new TUser();
        user.setId(58);
        user.setLoginAcct("888");
        user.setUserName("888");
        user.setEmail("888@77.com");
        Integer row = userService.updateUserInfo(user);
        if (row == 1) {
            System.out.println("success");
        }
    }

    @Test
    public void testDeleteUser() {
        List<Integer> idList = Arrays.asList(83,84);
        Integer row = userService.deleteUser(idList);
        System.out.println("delete number: " + row);
    }

    @Test
    public void testUUID() {
        String uuid = UUID.randomUUID().toString();
        String token = uuid + System.currentTimeMillis();
        System.out.println(token);
    }

    @Test
    public void testSendEmail() {
        String userEmail = "lisi@jar.me";
        String url = "http://127.0.0.1:8080/scw/login.html";
        ServiceUtils.sendEmailForFindPwd(userEmail, url);
    }

    @Test
    public void testsendMailForResetPwd() {
        String email = "lisi@jar.me";
        userService.sendMailForResetPwd(email);
    }
}
