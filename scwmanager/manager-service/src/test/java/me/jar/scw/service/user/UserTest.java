package me.jar.scw.service.user;

import com.github.pagehelper.PageInfo;
import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
        List<TUser> users = userService.findAllUsers(2, 5);
        for (TUser user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserAmount() {
        Integer num = userService.getUserAmount();
        System.out.println(num);
    }


    @Test
    public void testCreateNewUser() {
        TUser user = new TUser();
        user.setUserName("Jerry");
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
}
