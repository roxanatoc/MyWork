package model;

import java.util.Date;

public class Account {

    private Long id;
    private String type;
    private double amountOfMoney;
    private Date creationDate;
    private Long CNPClient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCNPClient() {
        return CNPClient;
    }

    public void setCNPClient(Long CNPClient) {
        this.CNPClient = CNPClient;
    }
}
