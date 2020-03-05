package me.jar.scw.service.user;

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
        List<TUser> users = userService.findAllUsers();
        for (TUser user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testCreateNewUser() {
        TUser user = new TUser();
        user.setUserName("Jerry2");
        user.setUserPswd("123456");
        user.setEmail("12@33.com");
        boolean result = userService.createNewUser(user);
        System.out.println(result);
        System.out.println("id: " + user.getId());
    }
}
