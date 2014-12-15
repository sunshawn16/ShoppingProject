package com.tw.shopping.promotion;

import com.tw.shopping.util.Parse;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class SecondHalfParseTest {
    @Test
    public void should_return_list_when_do_readToList() throws Exception {

        BufferedReader reader=mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("Item00001").thenReturn(null);;

        SecondHalfParse secondHalfParse=new SecondHalfParse();
        List<SecondHalfItem> secondHalfItemList=secondHalfParse.readToList(reader);
        //verify(reader,times(2)).readLine();
        assertThat(secondHalfItemList.get(0).getProductId(),is("Item00001"));

    }

    @Test
    public void should_get_secontHalfList_when_do_convert() throws Exception {
        String line="ITEM00001";
        SecondHalfItem secondHalfItem;
        Parse secondHalfParse= new SecondHalfParse();

        secondHalfItem = (SecondHalfItem)secondHalfParse.convert(line);


        assertThat(secondHalfItem.getProductId(),is("ITEM00001"));

    }




}