package com.tw.shopping.promotion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

public class SecondHalfParseTest {


    @Test
    public void should_get_secontHalfList_when_do_parse() throws Exception {
        SecondHalfParse mockSecondHalfParse=mock(SecondHalfParse.class);
        List<SecondHalfItem> secondHalfItemList=new ArrayList<SecondHalfItem>();
        SecondHalfItem secondHalfItem=new SecondHalfItem();
        secondHalfItem.setProductId("ITEM000001");
        secondHalfItemList.add(secondHalfItem);


        String path="./File/secondHalf_promotion----";


        when(mockSecondHalfParse.parse(path)).thenReturn(secondHalfItemList);


        List<SecondHalfItem> results= (List<SecondHalfItem>) mockSecondHalfParse.parse(path);

        verify(mockSecondHalfParse).parse(path);
        //6.断言
        assertThat(results.get(0).getProductId(),is("ITEM000001"));

    }




}