package me.jar.scw.manager.dao.permission;

import me.jar.scw.manager.dao.TPermissionMapper;
import me.jar.scw.manager.model.TPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
public class PermissionTest {
    @Autowired
    TPermissionMapper mapper;

    @Test
    public void testSelectAllMenu() {
        List<TPermission> permissions = mapper.selectAllMenu();
        for (TPermission permission : permissions) {
            System.out.println("第" + permission.getId() + "个菜单：" + permission.getName());
        }
    }

    @Test
    public void testSelectPermissionIdByRoleId() {
        String roleId = "7";
        List<Integer> list = mapper.selectPermissionIdByRoleId(roleId);
        list.forEach(pid -> System.out.println("pid: " + pid));
    }
}
