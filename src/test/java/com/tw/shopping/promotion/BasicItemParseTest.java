package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BasicItemParseTest {
    @Test
    public void should_get_basicItemList_when_do_parse() throws Exception {
        BasicItemParse mockBasicItemParse=mock(BasicItemParse.class);
        List<Item> basicItemList=new ArrayList<Item>();
        Item item=new Item();
        item.setProductId("ITEM000001");
        item.setOrigPrice(50.0);
        basicItemList.add(item);


        String path="./File/secondHalf_promotion----";


        when(mockBasicItemParse.parse(path)).thenReturn(basicItemList);


        List<Item> results= (List<Item>) mockBasicItemParse.parse(path);

        verify(mockBasicItemParse).parse(path);
        //6.断言
        assertThat(results.get(0).getProductId(),is("ITEM000001"));

    }
}