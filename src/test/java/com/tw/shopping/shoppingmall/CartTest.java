package com.tw.shopping.shoppingmall;

import com.tw.shopping.product.Item;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {
    @Test
    public void should_get_settle_when_list_has_different_products() throws Exception {

        List<Item> cartList= new ArrayList<Item>();
        Item item1=new Item();
        Item item2=new Item();

        item1.setProductId("ITEM000001");

        item1.setNum(1);
        item2.setProductId("ITEM000002");
        item2.setNum(3);
        cartList.add(item1);
        cartList.add(item2);

        CartImpl cart =new CartImpl();
        List<Item> finalCart=cart.t_settleCart(cartList);

        assertThat(finalCart.get(0).getNum(),is(1));
    }

    @Test
    public void should_get_settle_when_list_has_same_products() throws Exception {

        List<Item> cartList= new ArrayList<Item>();
        Item item1=new Item();
        Item item2=new Item();
        Item item3=new Item();
        item1.setProductId("ITEM000001");
        item1.setNum(1);
        item2.setProductId("ITEM000002");
        item2.setNum(3);
        item3.setProductId("ITEM000001");
        item3.setNum(5);
        cartList.add(item1);
        cartList.add(item2);
        cartList.add(item3);

        CartImpl cart =new CartImpl();
        List<Item> finalCart=cart.t_settleCart(cartList);

        assertThat(finalCart.get(0).getNum(),is(6));


    }

    @Test
    public void should_initial_price_when_do_initial() throws Exception {

        CartImpl cart =new CartImpl();
        List<Item>  basicItemList=new ArrayList<Item>();
        Item item= new Item();
        item.setProductId("Item000001");
        item.setOrigPrice(50);
        basicItemList.add(item);
        List<Item>  cartList=new ArrayList<Item>();
        Item item1= new Item();
        item1.setProductId("Item000001");
        cartList.add(item1);

        cart.t_InitialOrigPrice(cartList, basicItemList);

        assertEquals(50,cartList.get(0).getOrigPrice(),0.5);

    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

//    @Test
//    public void should_have_problem_when_path_is_wrong() throws Exception {
//
//        Cart cart = new Cart();
//        thrown.expect(IOException.class);
//        thrown.expectMessage("error");
//        cart.t_initialCart("adasdasdsa");
//
//    }
}