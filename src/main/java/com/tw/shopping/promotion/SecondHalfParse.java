package com.tw.shopping.promotion;

import com.tw.shopping.util.Parse;

/**
 * Created by sun on 14-12-5.
 */
public class SecondHalfParse extends Parse<SecondHalfItem> {

    @Override
    public SecondHalfItem convert(String line) {
        SecondHalfItem secondHalfItem=new SecondHalfItem();
        secondHalfItem.setProductId(line);
        return secondHalfItem;
    }
}
