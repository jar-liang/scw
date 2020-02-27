package me.jar.scw.connection;

import me.jar.scw.manager.model.TUser;
import me.jar.scw.manager.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description
 * @Date 2020/2/24-21:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
public class UserTest {
    @Test
    public void testCreateUser() {
        UserServiceImpl userService = new UserServiceImpl();
        TUser user = new TUser();
        user.setLoginAcct("manager");
        user.setUserName("Tom");
        user.setUserPswd("123456");
        user.setEmail("123@qq.com");
        user.setCreateTime("2020-02-24");
        Integer row = userService.createUser(user);
        if (row == 1) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
