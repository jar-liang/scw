package me.jar.scw.manager.model.vo;

import me.jar.scw.manager.model.TPermission;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8-23:19
 */
public class PermissionVO {
    private Integer id;
    private Integer pid;
    private String name;
    private String icon;
    private String url;
    private List<PermissionVO> childMenu;

    public PermissionVO() {
    }

    public PermissionVO(TPermission permission) {
        this.id = permission.getId();
        this.pid = permission.getPid();
        this.name = permission.getName();
        this.icon = permission.getIcon();
        this.url = permission.getUrl();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PermissionVO> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<PermissionVO> childMenu) {
        this.childMenu = childMenu;
    }
}
