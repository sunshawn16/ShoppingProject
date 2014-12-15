package com.tw.shopping.promotion;

import com.tw.shopping.util.Parse;

/**
 * Created by sun on 14-12-5.
 */
public class DiscountParse extends Parse<DiscountItem> {


    @Override
    public DiscountItem convert(String line) {
        String a[]=line.split(":");
        DiscountItem discountItem=new DiscountItem();
        discountItem.setProductId(a[0]);
        discountItem.setDiscountPercentage(Integer.parseInt(a[1]));
        return discountItem;
    }


}
