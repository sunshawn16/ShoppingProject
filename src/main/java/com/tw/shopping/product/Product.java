package com.tw.shopping.product;

/**
 * Created by sun on 14-12-4.
 */
public abstract class Product {
    protected String productId;
    protected double origPrice;
    protected int num;
    protected double disPrice;
    protected double payment;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(double origPrice) {
        this.origPrice = origPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public double getDisPrice() {
        return disPrice= getOrigPrice();
    }

    public void setDisPrice(double disPrice) {
        this.disPrice = disPrice;
    }


    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public abstract void cost();

}
