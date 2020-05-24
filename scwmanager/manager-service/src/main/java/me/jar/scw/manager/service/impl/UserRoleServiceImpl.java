package me.jar.scw.manager.service.impl;

import me.jar.scw.manager.dao.TUserRoleMapper;
import me.jar.scw.manager.model.TRole;
import me.jar.scw.manager.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
