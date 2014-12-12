package com.tw.shopping.promotion;

import com.tw.shopping.shoppingmall.ShoppingMall;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SpecialOfferTest {
    @Test
    public void should_get_reduction_when_payment_full_hundred() throws Exception {
        ShoppingMall shoppingMall= new ShoppingMall();
        shoppingMall.setDisPayment(120.0);

        assertThat(SpecialOffer.specialOfferHundredMinus(2,shoppingMall.getDisPayment()),is(118.0));

    }

    @Test
    public void should_not_get_reduction_when_payment_not_full_hundred_() throws Exception {
        ShoppingMall shoppingMall = new ShoppingMall();
        shoppingMall.setDisPayment(80);

        assertThat(SpecialOffer.specialOfferHundredMinus(3,shoppingMall.getDisPayment()),is(80.0));

    }
}