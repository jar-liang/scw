package me.jar.scw.manager.service;

import me.jar.scw.manager.model.vo.PermissionVO;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:37
 */
public interface IPermissionService {

    /**
     *  找出所有菜单，分好子父级
     * @return
     */
    List<PermissionVO> findAllMemu();
}
