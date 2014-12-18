package com.tw.shopping.promotion;

import com.tw.shopping.product.ProductImpl;

/**
 * Created by sun on 14-12-4.
 */
public class HundredMinus extends Promotion {
    private double reduction;
    ProductImpl productImpl;

    public HundredMinus(ProductImpl productImpl,double reduction){this.productImpl = productImpl;this.reduction=reduction;}

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    @Override
    public void cost() {
        productImpl.cost();

        this.productId= productImpl.getProductId();
        this.num= productImpl.getNum();
        this.origPrice = productImpl.getOrigPrice();
        this.disPrice= productImpl.getDisPrice();
        int numberHundred=(int) productImpl.getPayment()/100;
        this.payment = productImpl.getPayment()-numberHundred*reduction;

    }


}
