package me.jar.scw.manager.dao.userrole;

import me.jar.scw.manager.dao.TUserRoleMapper;
import me.jar.scw.manager.model.TRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
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
        Integer id = 45;
        List<TRole> roleList = userRoleMapper.selectRoleByUserId(id);
        for (TRole role : roleList) {
            System.out.println(role.getId() + " : " + role.getName());
        }
    }

    @Test
    public void testSelectAllRole() {
        List<TRole> roleList = userRoleMapper.selectAllRole();
        roleList.forEach(role -> System.out.println(role.getId() + " : " + role.getName()));
    }

    @Test
    public void testInsertRoleByUserId() {
        Integer userId = 55;
        List<Integer> roleIdList = Arrays.asList(1,3,5);
        Integer row = userRoleMapper.insertRoleByUserId(userId, roleIdList);
        System.out.println(row);
    }

    @Test
    public void testDeleteRoleIdUserId() {
        Integer userId = 68;
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(6);
        Integer row = userRoleMapper.deleteRoleIdUserId(userId, roleIdList);
        System.out.println(row);
    }

    @Test
    public void testSelectRoleByKeyWord() {
        List<TRole> roleList = userRoleMapper.selectRoleByKeyWord(0, 10, "经理");
        roleList.forEach(role -> System.out.println(role.getName()));
    }

    @Test
    public void testCountRoleNumByKeyWord() {
        Integer row = userRoleMapper.countRoleNumByKeyWord("师");
        System.out.println(row);
    }

    @Test
    public void testSelectTenRole() {
        List<TRole> roleList = userRoleMapper.selectTenRole();
        roleList.forEach(role -> System.out.println(role.getName()));
    }

    @Test
    public void testCheckRoleExist() {
        String roleName = "管理员1";
        Integer row = userRoleMapper.checkRoleExist(roleName);
        if (row > 0) {
            System.out.println("exist");
        } else {
            System.out.println("not exist");
        }
    }

    @Test
    public void testInsertNewRoleByName() {
        String roleName = "测试员1";
        Integer row = userRoleMapper.insertNewRoleByName(roleName);
        if (row == 1) {
            System.out.println("insert success");
        } else {
            System.out.println("insert fail");
        }
    }
}
