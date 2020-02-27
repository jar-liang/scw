package me.jar.scw.manager.model;

/**
 * @Description
 * @Date 2020/2/23-15:57
 */
public class TProjectTag {
    private Integer id;
    private Integer projectId;
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
