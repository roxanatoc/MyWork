package model;

public class Client {

    private Long CNP;
    private String name;
    private int cardNumber;
    private String address;

    public Long getCNP() {
        return CNP;
    }

    public void setCNP(Long CNP) {
        this.CNP = CNP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
