package be.vdab.data;

import java.io.Serializable;

public class Beer implements Serializable {
    private int id;
    private String name;
    private float price;
    private int stock;
    private float alcohol;
    private int category;
    private int brewer;

    public int getBrewer() {
        return brewer;
    }

    public void setBrewer(int brewer) {
        this.brewer = brewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", alcohol=" + alcohol +
                '}';
    }
}
