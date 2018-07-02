package com.example.demo.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetail {

    @NotNull
    @Size(min=5, max=16)
    private String firstName;

    @NotNull
    @Size(min=5, max=16)
    private String lastName;

    @NotNull
    @Size(min=5, max=16)
    private String userName;

    public UserDetail() {
    }

    public UserDetail(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
