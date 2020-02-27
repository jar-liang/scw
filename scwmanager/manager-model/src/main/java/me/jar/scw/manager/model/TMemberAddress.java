package me.jar.scw.manager.model;

/**
 * @Description
 * @Date 2020/2/23-16:05
 */
public class TMemberAddress {
    private Integer id;
    private Integer memberId;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
