package com.tw.shopping.shoppingmall;

import com.tw.shopping.product.Item;

import java.util.List;

/**
 * Created by sun on 14-12-18.
 */
public interface Cart {
    public List<Item> InitialCart(List<Item> cartList, List<Item> basicList);

}
