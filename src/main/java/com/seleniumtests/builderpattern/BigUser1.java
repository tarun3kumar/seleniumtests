package com.seleniumtests.builderpattern;

public class BigUser1 {

    private String firstName;
    private String lastName;
    private String userName;
    private String address1;
    private String address2;
    private String phone1;
    private String phone2;
    private String password;
    private String confirmPassword;

    public BigUser1(String firstName, String lastName, String userName, String address1, String address2, String phone1, String phone2, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.address1 = address1;
        this.address2 = address2;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.password = password;
        this.confirmPassword = confirmPassword;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
