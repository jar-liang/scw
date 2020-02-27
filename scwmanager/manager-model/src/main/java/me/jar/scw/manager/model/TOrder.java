package me.jar.scw.manager.model;

/**
 * @Description
 * @Date 2020/2/23-16:00
 */
public class TOrder {
    private Integer id;
    private Integer memberId;
    private Integer projectId;
    private Integer returnId;
    private String orderNum;
    private String createDate;
    private Integer money;
    private Integer rtnCount;
    private String status;
    private String address;
    private String invoice;
    private String invoicTitle;
    private String remark;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getRtnCount() {
        return rtnCount;
    }

    public void setRtnCount(Integer rtnCount) {
        this.rtnCount = rtnCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoicTitle() {
        return invoicTitle;
    }

    public void setInvoicTitle(String invoicTitle) {
        this.invoicTitle = invoicTitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
