package me.jar.scw.manager.model;

/**
 * @Description
 * @Date 2020/2/23-15:39
 */
public class TReturn {
    private Integer id;
    private Integer projectId;
    private String type;
    private Integer supportMoney;
    private String content;
    private Integer count;
    private Integer signalPurchase;
    private Integer purchase;
    private Integer freight;
    private String invoice;
    private Integer rtnDate;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSignalPurchase() {
        return signalPurchase;
    }

    public void setSignalPurchase(Integer signalPurchase) {
        this.signalPurchase = signalPurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Integer getRtnDate() {
        return rtnDate;
    }

    public void setRtnDate(Integer rtnDate) {
        this.rtnDate = rtnDate;
    }
}
