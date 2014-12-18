package com.tw.shopping.Module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tw.shopping.shoppingmall.Cart;
import com.tw.shopping.shoppingmall.CartImpl;

/**
 * Created by sun on 14-12-18.
 */
public class CartModule implements Module{

    @Override
    public void configure(Binder binder) {
        binder.bind(Cart.class).to(CartImpl.class);
    }
}
