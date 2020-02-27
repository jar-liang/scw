package me.jar.scw.manager.model;

/**
 * @Description
 * @Date 2020/2/23-16:03
 */
public class TMemberProjectFollow {
    private Integer id;
    private Integer projectId;
    private Integer memberId;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
