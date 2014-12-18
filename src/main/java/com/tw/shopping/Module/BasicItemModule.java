package com.tw.shopping.Module;

import com.google.inject.Module;
import com.google.inject.Binder;
import com.tw.shopping.promotion.BasicItemParse;
import com.tw.shopping.util.Parse;

/**
 * Created by sun on 14-12-18.
 */
public class BasicItemModule implements Module {
    @Override
    public void configure(Binder binder) {

        binder.bind(Parse.class).to(BasicItemParse.class);
    }
}
