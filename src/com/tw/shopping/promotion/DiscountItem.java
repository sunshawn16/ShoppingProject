package com.tw.shopping.promotion;

/**
 * Created by sun on 14-12-5.
 */
public class DiscountItem {
    private String productId;
    private int discountPercentage;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
