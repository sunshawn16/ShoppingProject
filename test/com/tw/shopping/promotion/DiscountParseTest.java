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

        //1.先mock一个待测试类的对象
        DiscountParse mockDiscountParse=mock(DiscountParse.class);

        //2.创建执行完成后.希望返回的数据
        List<DiscountItem>  discountItemList=new ArrayList<DiscountItem>();
        DiscountItem discountItem=new DiscountItem();
        discountItem.setProductId("ITEM000001");
        discountItem.setDiscountPercentage(75);
        discountItemList.add(discountItem);

        //3.这个地址不重要,可以随意写,因为不会真正的去读文件
        String path="./File/discount_promotion----";

        //4.设定parse执行后的返回值,即第2步创建的那个discountItemList对象
        when(mockDiscountParse.parse(path)).thenReturn(discountItemList);

        //5.执行测试:执行parse方法,得到结果
        List<DiscountItem> results= ( List<DiscountItem>) mockDiscountParse.parse(path);

        verify(mockDiscountParse).parse(path);
        //6.断言
        assertThat(results.get(0).getProductId(),is("ITEM000001"));
    }

}