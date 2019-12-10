package com.example.bmcatering.Model;

public class User {
    private String Address;
    private String Confirm;
    private String Password;
    private String Phone;
    private String IsStaff;

    public User() {
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public User(String address, String confirm, String password, String phone) {
        Address = address;
        Confirm = confirm;
        Password = password;
        Phone = phone;
        IsStaff="false";
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getConfirm() {
        return Confirm;
    }

    public void setConfirm(String confirm) {
        Confirm = confirm;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
