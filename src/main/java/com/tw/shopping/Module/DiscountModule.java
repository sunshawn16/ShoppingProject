package com.tw.shopping.Module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tw.shopping.promotion.DiscountParse;
import com.tw.shopping.util.Parse;

/**
 * Created by sun on 14-12-18.
 */
public class DiscountModule implements Module{
    public void configure(Binder binder){
        binder.bind(Parse.class).to(DiscountParse.class);

    }
}
