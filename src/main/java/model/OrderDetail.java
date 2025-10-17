/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class OrderDetail {
    private int detail_id;
    private Orders order_id;
    ProductVariant variant_id;
    private int quantity;
    private double price;

    public OrderDetail(int detail_id, Orders order_id, ProductVariant variant_id, int quantity, double price) {
        this.detail_id = detail_id;
        this.order_id = order_id;
        this.variant_id = variant_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public Orders getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
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
        return "OrderDetail{" + "detail_id=" + detail_id + ", order_id=" + order_id + ", variant_id=" + variant_id + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
    
}
