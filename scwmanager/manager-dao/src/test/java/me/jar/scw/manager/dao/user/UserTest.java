package me.jar.scw.manager.dao.user;

import com.github.pagehelper.PageHelper;
import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
}
