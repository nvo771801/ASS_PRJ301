/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Category {
    private int cat_id;
    private String cat_name, cat_des;
    
    public Category(){
        
    }

    public Category(int cat_id, String cat_name, String cat_des) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_des = cat_des;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_des() {
        return cat_des;
    }

    public void setCat_des(String cat_des) {
        this.cat_des = cat_des;
    }

    @Override
    public String toString() {
        return "Category{" + "cat_id=" + cat_id + ", cat_name=" + cat_name + ", cat_des=" + cat_des + '}';
    }
    
    
}
