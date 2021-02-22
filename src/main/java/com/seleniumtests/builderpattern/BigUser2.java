package com.seleniumtests.builderpattern;

public class BigUser2 {

    private String firstName;
    private String lastName;
    private String userName;
    private String address1;
    private String address2;
    private String phone1;
    private String phone2;
    private String password;
    private String confirmPassword;

    private BigUser2(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        userName = builder.userName;
        address1 = builder.address1;
        address2 = builder.address2;
        phone1 = builder.phone1;
        phone2 = builder.phone2;
        password = builder.password;
        confirmPassword = builder.confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String userName;
        private String address1;
        private String address2;
        private String phone1;
        private String phone2;
        private String password;
        private String confirmPassword;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder address1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder phone1(String phone1) {
            this.phone1 = phone1;
            return this;
        }

        public Builder phone2(String phone2) {
            this.phone2 = phone2;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public BigUser2 build() {
            return new BigUser2(this);
        }
    }
}
