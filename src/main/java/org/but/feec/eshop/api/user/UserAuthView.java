package org.but.feec.eshop.api.user;

public class UserAuthView {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAuthView{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

