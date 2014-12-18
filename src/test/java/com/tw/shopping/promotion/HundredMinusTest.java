package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;
import com.tw.shopping.product.ProductImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class HundredMinusTest {
//    @Before
//    public void setUp(){ com.tw.shopping.product.Product item=new com.tw.shopping.product.Item();}

    @Test
    public void should_get_test_when_reduction_is_5() throws Exception {
        ProductImpl item=new Item();
        item.setOrigPrice(10);
        item.setNum(10);
        item=new HundredMinus(item,5);

        item.cost();

        assertEquals(95,item.getPayment(),0.001);

    }
}