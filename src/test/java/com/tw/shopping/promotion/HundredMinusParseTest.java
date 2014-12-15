package com.tw.shopping.promotion;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HundredMinusParseTest {
    @Test
    public void should_get_reduction_when_read_line() throws Exception {
        String line="ITEM000001:3";
        HundredMinusParse hundredMinusParse= new HundredMinusParse();
        HundredMinusItem hundredMinusItem=hundredMinusParse.convert(line);

        assertThat(hundredMinusItem.getReduction(),is(3.0));


    }
}