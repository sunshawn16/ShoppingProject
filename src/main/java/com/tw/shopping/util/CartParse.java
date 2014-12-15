package com.tw.shopping.util;

import com.tw.shopping.product.Item;
import com.tw.shopping.util.Parse;

/**
 * Created by sun on 14-12-5.
 */
public class CartParse extends Parse<Item> {


    @Override
    public Item convert(String line) {
        Item cartItem=new Item();
        String a[]=line.split(":");
        cartItem.setProductId(a[0]);
        cartItem.setNum(Integer.parseInt(a[1]));

        return cartItem;
    }
}
