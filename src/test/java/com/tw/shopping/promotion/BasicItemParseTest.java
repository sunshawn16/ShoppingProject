package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BasicItemParseTest {
    @Test
    public void should_get_basicList_when_do_readToList_() throws Exception {

        BufferedReader reader= mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("ITEM000001:60").thenReturn(null);
        BasicItemParse basicItemParse= new BasicItemParse();

        List<Item> basicItemList=basicItemParse.readToList(reader);

        assertThat(basicItemList.get(0).getProductId(),is("ITEM000001"));


    }

    @Test
    public void should_get_basicItemList_when_do_convert() throws Exception {
      //  BasicItemParse mockBasicItemParse=mock(BasicItemParse.class);
        String line= "ITEM000001:50";
        Item item=new Item();
        BasicItemParse basicItemParse=new BasicItemParse();
        item=basicItemParse.convert(line);

       // when(mockBasicItemParse.parse(path)).thenReturn(basicItemList);


      //  List<Item> results= (List<Item>) mockBasicItemParse.parse(path);

      //  verify(mockBasicItemParse).parse(path);
        //6.断言
        assertThat(item.getProductId(),is("ITEM000001"));

    }
}