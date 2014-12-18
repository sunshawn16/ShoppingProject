package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;
import com.tw.shopping.product.ProductImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountTest {
    ProductImpl item;

    @Before
    public void setup(){
        item=new Item();
        item.setOrigPrice(10);
        item.setNum(20);

    }


    @Test
    public void should_get_cost_when_discount_is_80() throws Exception {

        item=new Discount(item,80);

        item.cost();

        assertEquals(160.0,item.getPayment(),1);

    }

}