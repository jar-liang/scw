package me.jar.scw.manager.dao.user;

import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
