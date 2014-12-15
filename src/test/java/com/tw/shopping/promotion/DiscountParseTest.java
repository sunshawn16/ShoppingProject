package com.tw.shopping.promotion;

import org.junit.Test;

import java.lang.String;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DiscountParseTest {

    //mockito参考文档:
    // http://www.it165.net/pro/html/201403/10110.html


    /**
     * 测试 DiscountParse 类里的 parse() 方法:
     * @throws Exception
     */
    @Test
    public void should_get_DiscountList_when_do_parse() throws Exception {


        String line="ITEM000001:75";
        DiscountParse discountParse= new DiscountParse();
        DiscountItem discountItem= discountParse.convert(line);

        assertThat(discountItem.getDiscountPercentage(),is(75));
        assertThat(discountItem.getProductId(),is("ITEM000001"));



    }

}