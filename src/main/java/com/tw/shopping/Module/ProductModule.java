package com.tw.shopping.Module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tw.shopping.product.Item;
import com.tw.shopping.product.Product;

/**
 * Created by sun on 14-12-18.
 */
public class ProductModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Product.class).to(Item.class);
    }
}
