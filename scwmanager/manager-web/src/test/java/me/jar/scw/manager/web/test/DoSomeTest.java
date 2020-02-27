package me.jar.scw.manager.web.test;

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
 * @Date 2020/2/25-21:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
public class DoSomeTest {
    @Autowired
    IUserService userService;

    @Test
    public void testFindUsers() {
        List<TUser> users = userService.findAllUsers();
        for (TUser user : users) {
            System.out.println(user);
        }
    }
}
