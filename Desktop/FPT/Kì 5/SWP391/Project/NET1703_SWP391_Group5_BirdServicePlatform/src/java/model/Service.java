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
public class Service {

    private int ServiceID;
    private String ServiceName;
    private String ServiceDesc;
    private int ServiceTime;
    private float Price;
    private int CategoryID;
    private int customerID;

    public Service(int ServiceID, String ServiceName, String ServiceDesc, int ServiceTime, float Price, int CategoryID, int customerID) {
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.ServiceDesc = ServiceDesc;
        this.ServiceTime = ServiceTime;
        this.Price = Price;
        this.CategoryID = CategoryID;
        this.customerID = customerID;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public String getServiceDesc() {
        return ServiceDesc;
    }

    public void setServiceDesc(String ServiceDesc) {
        this.ServiceDesc = ServiceDesc;
    }

    public int getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(int ServiceTime) {
        this.ServiceTime = ServiceTime;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

   
}
