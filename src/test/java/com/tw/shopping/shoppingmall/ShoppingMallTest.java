package com.tw.shopping.shoppingmall;

import com.tw.shopping.promotion.Promotion;
import com.tw.shopping.promotion.PromotionSet;
import com.tw.shopping.shoppingmall.Cart;
import com.tw.shopping.shoppingmall.ShoppingMall;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ShoppingMallTest {
    @Test
    public void should_get_show_when_cart_comes() throws Exception {
        ShoppingMall shoppingMall= new ShoppingMall();

        Cart cart= new Cart();
        PromotionSet promotionSet = new PromotionSet();
        shoppingMall.setPromotionSet(promotionSet);
        shoppingMall.initialShoppingMall();

        shoppingMall.checkOut(cart.putInCart("./File/cart.txt",shoppingMall.getPromotionSet().getBasicItemList()));

    }
}