package me.jar.scw.service.permission;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
