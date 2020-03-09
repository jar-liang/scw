package me.jar.scw.manager.service.impl;

import me.jar.scw.manager.dao.TPermissionMapper;
import me.jar.scw.manager.model.TPermission;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:38
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    TPermissionMapper permissionMapper;

    /**
     *  找到所有菜单并分好子父级
     * @return
     */
    @Override
    public List<PermissionVO> findAllMemu() {
        // 先拿到所有菜单
        List<TPermission> permissions = permissionMapper.selectAllMenu();
        // 定义permissionVO的List
        List<PermissionVO> permissionVOs = new ArrayList<>();
        // 遍历permissions，将菜单放进permissionVOs中
        // 先试试非递归，两次循环，第一次拿到根菜单，第二次拿到各自的子菜单
        for (TPermission permission : permissions) {
            if (permission.getPid() == 0) {
                permissionVOs.add(new PermissionVO(permission));
            }
        }
        for (PermissionVO permissionVO : permissionVOs) {
            List<PermissionVO> child = new ArrayList<>();
            for (TPermission permission : permissions) {
                if (permissionVO.getId() == permission.getPid()) {
                    child.add(new PermissionVO(permission));
                }
            }
            permissionVO.setChildMenu(child);
        }
        return permissionVOs;
    }

}
