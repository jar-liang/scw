package me.jar.scw.service.permission;

import com.alibaba.fastjson.JSON;
import me.jar.scw.manager.model.TPermission;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2020/3/9-20:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc4test.xml", "classpath:mybatisconfig.xml"})
public class PermissionTest {
    @Autowired
    IPermissionService permissionService;

    @Test
    public void testFindAllMenu() {
        List<PermissionVO> allMemu = permissionService.findAllMemu();
        String jsonMenu = JSON.toJSONString(allMemu);
        System.out.println(jsonMenu);
    }

    @Test
    public void testFindPermission() {
        List<TPermission> permission = permissionService.findPermission();
        System.out.println(JSON.toJSONString(permission));
    }

    @Test
    public void testFindPermissionIdByRoleId() {
        List<Integer> pid = permissionService.findPermissionIdByRoleId("");
        pid.forEach(id -> System.out.println("pid: " + id));
    }

    @Test
    public void testUpdateRolePermission() {
        String roleId = "3";
        List<String> pIds = Arrays.asList("2", "3", "4");
        boolean flag = permissionService.updateRolePermission(roleId, pIds);
        System.out.println(flag);
    }

    @Test
    public void testDeleteRoleById() {
        List<String> roleIdList = Arrays.asList("11");
        Integer result = permissionService.deleteRoleById(roleIdList);
        System.out.println(result);
    }

}
