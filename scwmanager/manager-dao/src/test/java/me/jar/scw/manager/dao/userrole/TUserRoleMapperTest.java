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
        Integer userId = 55;
        List<Integer> roleIdList = new ArrayList<>();
        roleIdList.add(2);
        roleIdList.add(3);
        Integer row = userRoleMapper.deleteRoleIdUserId(userId, roleIdList);
        System.out.println(row);
    }
}
