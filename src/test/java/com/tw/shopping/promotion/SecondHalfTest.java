package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;
import com.tw.shopping.product.ProductImpl;
import org.junit.Test;


import static org.junit.Assert.*;

public class SecondHalfTest {
    @Test
    public void should_get_cost_when_item_is_one() throws Exception {

        ProductImpl item=new Item();
        item.setNum(1);
        item.setOrigPrice(20);
        item=new SecondHalf(item);
        item.cost();

        assertEquals(20, item.getPayment(), 1);

    }

    @Test
    public void should_get_cost_when_item_is_five() throws Exception {

        ProductImpl item=new Item();
        item.setNum(5);
        item.setOrigPrice(20);
        item=new SecondHalf(item);
        item.cost();

        assertEquals(80, item.getPayment(), 1);

    }


}

