package me.jar.scw.manager.dao.userrole;

import me.jar.scw.manager.dao.TUserRoleMapper;
import me.jar.scw.manager.model.TRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description
 * @Date 2020/5/24-17:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
public class TUserRoleMapperTest {
    @Autowired
    TUserRoleMapper userRoleMapper;

    @Test
    public void testSelectRoleByUserId() {
        Integer id = 74;
        List<TRole> roleList = userRoleMapper.selectRoleByUserId(id);
        for (TRole role : roleList) {
            System.out.println(role.getId() + " : " + role.getName());
        }
    }
}
