package org.but.feec.eshop.api.user;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDetailView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty userName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty accountName = new SimpleStringProperty();
    private StringProperty accountPassword = new SimpleStringProperty();


    public Long getId() {
        return idProperty().get();
    }

    public void setId(Long id) {
        this.idProperty().setValue(id);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public void setEmail(String email) {
        this.emailProperty().setValue(email);
    }

    public String getUserName() {
        return userNameProperty().get();
    }

    public void setUserName(String userName) {
        this.userNameProperty().setValue(userName);
    }

    public String getAccountName() {
        return accountNameProperty().get();
    }

    public void setAccountName(String accountName) {
        this.accountNameProperty().setValue(accountName);
    }

    public String getStreet() {
        return streetProperty().get();
    }

    public void setStreet(String street) {
        this.streetProperty().setValue(street);
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty accountNameProperty() {
        return accountName;
    }

    public StringProperty streetProperty() {
        return accountPassword;
    }



}

