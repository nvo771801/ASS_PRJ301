/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Cart {
    private int cart_id;
    Customer cus_id;
    ProductVariant variant_id;
    private int quantity;
    private double price;
    
    public Cart(){
        
    }

    public Cart(int cart_id, Customer cus_id, ProductVariant variant_id, int quantity, double price) {
        this.cart_id = cart_id;
        this.cus_id = cus_id;
        this.variant_id = variant_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public Customer getCus_id() {
        return cus_id;
    }

    public void setCus_id(Customer cus_id) {
        this.cus_id = cus_id;
    }

    public ProductVariant getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(ProductVariant variant_id) {
        this.variant_id = variant_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart{" + "cart_id=" + cart_id + ", cus_id=" + cus_id + ", variant_id=" + variant_id + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}
