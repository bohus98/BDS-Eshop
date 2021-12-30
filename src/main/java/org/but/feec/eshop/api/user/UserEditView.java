package org.but.feec.eshop.api.user;

public class UserEditView {
    private Long id;
    private String userName;
    private String accountName;
    private String accountPassword;
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    @Override
    public String toString() {
        return "UserEditView{" +
                "userName='" + userName + '\'' +
                ", password='" + accountPassword + '\'' +
                ", accountName='" + accountName + '\'' +
                ", email='" + email +
                '}';
    }
}

