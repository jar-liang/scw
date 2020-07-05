package me.jar.scw.manager.service.impl;

import me.jar.scw.manager.dao.TPermissionMapper;
import me.jar.scw.manager.dao.TUserRoleMapper;
import me.jar.scw.manager.model.TPermission;
import me.jar.scw.manager.model.vo.PermissionVO;
import me.jar.scw.manager.service.IPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:38
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    TPermissionMapper permissionMapper;

    @Autowired
    private TUserRoleMapper userRoleMapper;

    // 删除角色结果，1代表成功，2代表由于外键约束删除失败，3代表其它sql错误删除失败
    private final Integer DELETE_SUCCESS = 1;
    private final Integer DELETE_FAIL_RESTRAIN = 2;
    private final Integer DELETE_FAIL_OTHER = 3;

    /**
     *  找到所有菜单并分好子父级
     * @return
     */
    @Override
    public List<PermissionVO> findAllMemu() {
        List<PermissionVO> permissionVOList = new ArrayList<>();
        try {
            // 先拿到所有菜单
            permissionVOList = getPermissionVOList(permissionMapper.selectAllMenu());
        } catch (DataAccessException e) {
            System.out.println("find all menu fail");
        }
        return permissionVOList;
    }

    @Override
    public List<PermissionVO> findMenuByUserId(Integer userId) {
        List<PermissionVO> permissionVOList = new ArrayList<>();
        try {
            permissionVOList = getPermissionVOList(permissionMapper.selectMenuByUserId(userId));
        } catch (DataAccessException e) {
            System.out.println("fail to find menu by user id");
        }
        return permissionVOList;
    }

    @Override
    public List<TPermission> findPermission() {
        try {
            return permissionMapper.selectAllMenu();
        } catch (DataAccessException e) {
            System.out.println("find permission fail");
        }
        return null;
    }

    @Override
    public List<Integer> findPermissionIdByRoleId(String roleId) {
        try {
            return permissionMapper.selectPermissionIdByRoleId(roleId);
        } catch (DataAccessException e) {
            System.out.println("fail to find permission id by role id");
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor=DataAccessException.class) // 添加rollbackFor，可以选择哪些异常会回滚
    public boolean updateRolePermission(String roleId, List<String> permissionIds) {
        permissionMapper.deletePermissionIdByRoleId(roleId);
        permissionMapper.insertPermissionWithRole(roleId, permissionIds);
        return true;
    }

    @Override
    public Integer deleteRoleById(List<String> roleIdList) {
        // 3：代表其它
        Integer result = DELETE_FAIL_OTHER;
        try {
            Integer row = userRoleMapper.deleteRoleById(roleIdList);
            if (row != null) {
                result = DELETE_SUCCESS;
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("delete fail due to foreign key restrain");
            result = DELETE_FAIL_RESTRAIN;
        } catch (DataAccessException e) {
            System.out.println("other reason, delete fail");
        }
        return result;
    }

    @Override
    public Integer deleteAllPermission(String roleId) {
        try {
            return permissionMapper.deletePermissionIdByRoleId(roleId);
        } catch (DataAccessException e) {
            System.out.println("delete permission fail");
        }
        return null;
    }

    /**
     *  将查询出来的所有菜单分出子父菜单
     * @param permissions
     * @return
     */
    private List<PermissionVO> getPermissionVOList(List<TPermission> permissions) {
        if (CollectionUtils.isEmpty(permissions)) {
            return new ArrayList<>();
        }
        // 定义permissionVO的List
        List<PermissionVO> permissionVOs = new ArrayList<>();
        // 遍历permissions，将菜单放进permissionVOs中
        // 两次循环，第一次拿到根菜单，第二次拿到各自的子菜单
        for (TPermission permission : permissions) {
            if (permission.getPid() == 0) {
                // 存放子菜单的List
                List<PermissionVO> subPermissionVOs = new ArrayList<>();
                for (TPermission p : permissions) {
                    if (p.getPid().equals(permission.getId())) {
                        subPermissionVOs.add(new PermissionVO(p));
                    }
                }
                // 父菜单
                PermissionVO permissionVO = new PermissionVO(permission);
                // 将子菜单List添加到父菜单下，先排序
                Collections.sort(subPermissionVOs, (o1, o2) -> o1.getId() - o2.getId());
                permissionVO.setChildMenu(subPermissionVOs);
                // 将父菜单添加到父菜单List
                permissionVOs.add(permissionVO);
            }
        }
        // 对父菜单排序
        Collections.sort(permissionVOs, (o1, o2) -> o1.getId() - o2.getId());
        return permissionVOs;
    }

}
