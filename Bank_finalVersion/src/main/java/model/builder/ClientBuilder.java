package model.builder;

import model.Client;

public class ClientBuilder {

    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setCardNumber(int cardNumber) {
        client.setCardNumber(cardNumber);
        return this;
    }

    public ClientBuilder setCNP(Long CNP){
        client.setCNP(CNP);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public Client build() {
        return client;
    }
}
