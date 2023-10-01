package com.example.testfb4.Products;

public class model {
    String name, price, ingre, purl;

    model(){

    }

    public model(String name, String price, String ingre, String purl) {
        this.name = name;
        this.price = price;
        this.ingre = ingre;
        this.purl = purl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngre() {
        return ingre;
    }

    public void setIngre(String ingre) {
        this.ingre = ingre;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
