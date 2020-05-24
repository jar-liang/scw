package me.jar.scw.service.userrole;

import com.alibaba.fastjson.JSON;
import me.jar.scw.manager.model.TRole;
import me.jar.scw.manager.service.IUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description
 * @Date 2020/5/24-18:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
public class UserRoleServiceImplTest {
    @Autowired
    IUserRoleService userRoleService;

    @Test
    public void testFindUserRoleById() {
        Integer id = 74;
        List<TRole> roleList = userRoleService.findUserRoleById(id);
        System.out.println(JSON.toJSONString(roleList));
    }

}
