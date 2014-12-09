package com.tw.shopping.promotion;

import com.tw.shopping.product.Product;

/**
 * Created by sun on 14-12-4.
 */
public class HundredMinus extends Promotion {
    private double reduction;
    Product product;

    public HundredMinus(Product product,double reduction){this.product=product;this.reduction=reduction;}

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    @Override
    public void cost() {
        product.cost();

        this.productId=product.getProductId();
        this.num=product.getNum();
        this.origPrice =product.getOrigPrice();
        this.disPrice=product.getDisPrice();
        int numberHundred=(int)product.getPayment()/100;
        this.payment =product.getPayment()-numberHundred*reduction;

    }


}
