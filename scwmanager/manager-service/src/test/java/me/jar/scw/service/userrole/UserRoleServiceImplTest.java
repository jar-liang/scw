package me.jar.scw.service.userrole;

import com.alibaba.fastjson.JSON;
import me.jar.scw.manager.model.TRole;
import me.jar.scw.manager.service.IUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testFindAllRoles() {
        List<TRole> allRoles = userRoleService.findAllRoles();
        allRoles.forEach(role -> System.out.println(role.getId() + " : " + role.getName()));
    }

    @Test
    public void testGetPartRoles() {
        Integer id = 74;
        Map<String, List<TRole>> partRoles = userRoleService.getPartRoles(id);
        List<TRole> ownRoleList = partRoles.get("own");
        List<TRole> notOwnRoleList = partRoles.get("notOwn");
        ownRoleList.forEach(role -> System.out.println("拥有： " + role.getName()));
        notOwnRoleList.forEach(role -> System.out.println("未拥有： " + role.getName()));
    }

    @Test
    public void testAddRoles() {
        Integer userId = 55;
        List<Integer> addRoleList = new ArrayList<>();
        addRoleList.add(2);
        addRoleList.add(4);
        Integer row = userRoleService.addRoles(userId, addRoleList);
        System.out.println(row);
    }

    @Test
    public void testDeleteUserRole() {
        Integer userId = 55;
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(1);
        Integer row = userRoleService.deleteUserRole(userId, roleIdList);
        System.out.println(row);
    }

    @Test
    public void testFindUserRoleByKeyWord() {
        Integer pageNum = 1;
        Integer pageSize = 10;
        String searchKey = "h";
        List<TRole> roles = userRoleService.findUserRoleByKeyWord(pageNum, pageSize, searchKey);
        roles.forEach(role -> System.out.println(role.getName()));
    }

    @Test
    public void testGetRoleAmount() {
        Integer amount = userRoleService.getRoleAmount("j");
        System.out.println(amount);
    }

    @Test
    public void testFindTenRoles() {
        List<TRole> tenRoles = userRoleService.findTenRoles();
        tenRoles.forEach(role -> System.out.println(role.getName()));
    }

}
