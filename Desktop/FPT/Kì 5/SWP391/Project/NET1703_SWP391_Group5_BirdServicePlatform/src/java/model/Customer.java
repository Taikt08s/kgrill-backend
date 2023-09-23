/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author TINH KHUNG
 */
public class Customer {

    private int customerID;
    private String customerName;
    private String Address;
    private String PhoneNumber;
    private String RoleName;

    public Customer(int customerID, String customerName, String Address, String PhoneNumber, String RoleName) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.RoleName = RoleName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
    
    
}
