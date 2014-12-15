package com.tw.shopping.promotion;

import com.tw.shopping.util.Parse;

/**
 * Created by sun on 14-12-5.
 */
public class HundredMinusParse extends Parse<HundredMinusItem> {

    @Override
    public HundredMinusItem convert(String line) {
        HundredMinusItem hundredMinusItem=new HundredMinusItem();
        String a[]=line.split(":");
        hundredMinusItem.setProductId(a[0]);
        hundredMinusItem.setReduction(Double.parseDouble(a[1]));

        return hundredMinusItem;
    }
}
