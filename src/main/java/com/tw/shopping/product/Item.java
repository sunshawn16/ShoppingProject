package com.tw.shopping.product;

/**
 * Created by sun on 14-12-4.
 */
public class Item extends Product {

    private double disPrice;

    @Override
    public double getDisPrice() {
        return disPrice;
    }

    @Override
    public void setDisPrice(double disPrice) {
        this.disPrice = disPrice;
    }

    @Override
    public void cost() {
        this.disPrice= origPrice;
        this.payment = origPrice *num;
    }

}
