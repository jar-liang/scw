package me.jar.scw.manager.model;

/**
 * @Description
 * @Date 2020/2/23-16:09
 */
public class TAccountTypeCert {
    private Integer id;
    private String acctType;
    private Integer certId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public Integer getCertId() {
        return certId;
    }

    public void setCertId(Integer certId) {
        this.certId = certId;
    }
}
