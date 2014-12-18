package com.tw.shopping.Module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tw.shopping.shoppingmall.ShoppingMall;
import com.tw.shopping.shoppingmall.ShoppingMallImpl;

/**
 * Created by sun on 14-12-18.
 */
public class ShoppingMallModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ShoppingMall.class).to(ShoppingMallImpl.class);
    }
}
