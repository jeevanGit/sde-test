package com.overbond.entities;

public class AbstractBond {

    private String id;
    private String type;
    private String tenor;
    private String yield;
    private Integer amount_outstanding;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public Integer getAmount_outstanding() {
        return amount_outstanding;
    }

    public void setAmount_outstanding(Integer amount_outstanding) {
        this.amount_outstanding = amount_outstanding;
    }
}
