/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Orders {
    private int order_id;
    Customer cus_id;
    private String order_date;
    private double total;
    private String status;
    
    public Orders(){
        
    }

    public Orders(int order_id, Customer cus_id, String order_date, double total, String status) {
        this.order_id = order_id;
        this.cus_id = cus_id;
        this.order_date = order_date;
        this.total = total;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customer getCus_id() {
        return cus_id;
    }

    public void setCus_id(Customer cus_id) {
        this.cus_id = cus_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" + "order_id=" + order_id + ", cus_id=" + cus_id + ", order_date=" + order_date + ", total=" + total + ", status=" + status + '}';
    }
    
}
