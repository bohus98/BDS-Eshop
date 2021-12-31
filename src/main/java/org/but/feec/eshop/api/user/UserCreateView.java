package org.but.feec.eshop.api.user;

import java.util.Arrays;

public class UserCreateView {
    private String userName;
    private String accountName;
    private String email;
    private char[] pwd;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public char[] getPwd() {
        return pwd;
    }

    public void setPwd(char[] pwd) {
        this.pwd = pwd;
    }




    @Override
    public String toString() {
        return "UserCreateView{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", pwd=" + Arrays.toString(pwd) +
                '}';
    }
}
