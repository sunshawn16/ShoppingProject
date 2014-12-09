package com.tw.shopping;

import com.tw.shopping.product.Item;
import com.tw.shopping.promotion.PromotionSet;
import com.tw.shopping.shoppingmall.Cart;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

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

        Cart cart = new Cart();
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

        Cart cart = new Cart();
        List<Item> finalCart=cart.t_settleCart(cartList);

        assertThat(finalCart.get(0).getNum(),is(6));


    }



}