package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    private static final int CNP_LENGHT = 13;

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Client client;

    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateCNP(client.getCNP());
        return errors.isEmpty();
    }

    private void validateCNP(Long CNP) {
        if (String.valueOf(CNP).length() != CNP_LENGHT) {
            errors.add("Invalid CNP");
        }
    }
}
