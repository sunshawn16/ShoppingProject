package com.tw.shopping.promotion;

import com.tw.shopping.product.*;

/**
 * Created by sun on 14-12-4.
 */
public class Discount extends Promotion {
    private int discountPercentage;
    Product product;

    public Discount(Product product,int discountPercentage){this.product=product;this.discountPercentage=discountPercentage;}

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void cost() {
        product.cost();

        this.productId=product.getProductId();
        this.num=product.getNum();
        this.origPrice =product.getOrigPrice();
        this.disPrice=product.getOrigPrice() *discountPercentage/100;
        this.payment=product.getPayment()*discountPercentage/100;

    }
}


