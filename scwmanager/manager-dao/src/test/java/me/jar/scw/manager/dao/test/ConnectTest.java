package me.jar.scw.manager.dao.test;

import com.alibaba.druid.pool.DruidDataSource;
import me.jar.scw.manager.dao.TUserMapper;
import me.jar.scw.manager.model.TUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description
 * @Date 2020/2/23-10:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectTest {
    ClassPathXmlApplicationContext context;

    @Autowired
    TUserMapper userMapper;

    @Before
    public void doBefor() {
        context = new ClassPathXmlApplicationContext("mybatisconfig.xml");
    }

    @After
    public void doAfter() {
        context.close();
    }

    @Test
    public void testConnection() throws SQLException {
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testInsert() {
        TUser user = new TUser();
        user.setLoginAcct("test2");
        user.setUserPswd("test2");
        user.setUserName("test");
        user.setEmail("test");
        user.setCreateTime("5555");
        Integer row = userMapper.insertUser(user);
        System.out.println(row);
    }

    @Test
    public void getAllUsers() {
        List<TUser> users = userMapper.selectAllUsers();
        for (TUser user : users) {
            System.out.println(user);
        }
    }
}
