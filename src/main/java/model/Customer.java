/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Customer {
    private int cus_id;
    private String fullname, email,pass,phone,address;
    
    public Customer(){
        
    }

    public Customer(int cus_id, String fullname, String email, String pass, String phone, String address) {
        this.cus_id = cus_id;
        this.fullname = fullname;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.address = address;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "cus_id=" + cus_id + ", fullname=" + fullname + ", email=" + email + ", pass=" + pass + ", phone=" + phone + ", address=" + address + '}';
    }
    
}
