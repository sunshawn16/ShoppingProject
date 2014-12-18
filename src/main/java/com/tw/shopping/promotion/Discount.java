package com.tw.shopping.promotion;

import com.tw.shopping.product.*;

/**
 * Created by sun on 14-12-4.
 */
public class Discount extends Promotion {
    private int discountPercentage;
    ProductImpl productImpl;

    public Discount(ProductImpl productImpl,int discountPercentage){this.productImpl = productImpl;this.discountPercentage=discountPercentage;}

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void cost() {
        productImpl.cost();

        this.productId= productImpl.getProductId();
        this.num= productImpl.getNum();
        this.origPrice = productImpl.getOrigPrice();
        this.disPrice= productImpl.getOrigPrice() *discountPercentage/100;
        this.payment= productImpl.getPayment()*discountPercentage/100;

    }
}


