package com.tw.shopping.shoppingmall;

import com.tw.shopping.product.Item;
import com.tw.shopping.product.ProductImpl;
import com.tw.shopping.promotion.PromotionGroup;

import java.util.List;

/**
 * Created by sun on 14-12-18.
 */
public interface ShoppingMall {

    //public void addPromotion(List<>);
    public void setPromotionGroup(PromotionGroup promotionGroup);
    public double getDisPayment();

    public void checkOut(List<Item> cartList);
    public void printTotal();
    public void printItem(ProductImpl item);
}
