package com.tw.shopping.promotion; /**
 * Created by sun on 14-12-5.
 */
import com.tw.shopping.product.Item;
import com.tw.shopping.util.Parse;

import java.lang.String;
public class BasicItemParse extends Parse<Item> {

    @Override
    public Item convert(String line) {
        String a[]=line.split(":");
        Item basicItem=new Item();
        basicItem.setProductId(a[0]);

        basicItem.setOrigPrice(Double.parseDouble(a[1]));

        return basicItem;
    }


}
