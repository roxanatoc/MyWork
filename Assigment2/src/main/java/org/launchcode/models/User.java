package org.launchcode.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity{

    @NotBlank
    @Size(min = 3, max = 25, message = "Size should be between 3 and 25 characters!")
    private String username;

    @NotBlank
    private String password;

    private String role;

    public User(@NotBlank @Size(min = 3, max = 25) String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
        this.role = "user";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

}