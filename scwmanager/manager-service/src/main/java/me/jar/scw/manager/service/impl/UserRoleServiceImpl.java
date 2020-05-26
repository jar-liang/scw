package me.jar.scw.manager.service.impl;

import me.jar.scw.manager.dao.TUserRoleMapper;
import me.jar.scw.manager.model.TRole;
import me.jar.scw.manager.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理用户角色的service实现类
 * @Date 2020/5/24-18:06
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    TUserRoleMapper userRoleMapper;

    @Override
    public List<TRole> findUserRoleById(Integer id) {
        try {
            return userRoleMapper.selectRoleByUserId(id);
        } catch (DataAccessException e) {
            System.out.println("find user role fail");
        }
        return null;
    }

    @Override
    public List<TRole> findAllRoles() {
        try {
            return userRoleMapper.selectAllRole();
        } catch (DataAccessException e) {
            System.out.println("find all roles fail");
        }
        return null;
    }

    @Override
    public Map<String, List<TRole>> getPartRoles(Integer id) {
        List<TRole> userOwnRoles = findUserRoleById(id);
        List<TRole> allRoles = findAllRoles();
        if (allRoles == null || userOwnRoles == null) {
            System.out.println("get user own roles and not own roles fail");
            return null;
        }
        allRoles.removeAll(userOwnRoles);
        Map<String, List<TRole>> rolesMap = new HashMap<>();
        rolesMap.put("own", userOwnRoles);
        rolesMap.put("notOwn", allRoles);
        return rolesMap;
    }
}
