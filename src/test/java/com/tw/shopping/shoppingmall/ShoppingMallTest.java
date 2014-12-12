package com.tw.shopping.shoppingmall;

import com.tw.shopping.product.Item;
import com.tw.shopping.product.Product;
import com.tw.shopping.promotion.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ShoppingMallTest {

    @Test
    public void should_display_msg() throws Exception {

        assertEquals(1,new ShoppingMall().printTotal());
    }

    @Test
    public void should_have_initialShoppingMall_when_do() throws Exception {
        ShoppingMall shoppingMall= new ShoppingMall();
//        Cart cart= new Cart();
        PromotionSet promotionSet = new PromotionSet();
        shoppingMall.setPromotionSet(promotionSet);

        shoppingMall.initialShoppingMall();


        assertEquals(3,promotionSet.getBasicItemList().size());
        assertEquals(2,promotionSet.getDiscountItemList().size());


        assertEquals(1,promotionSet.getHundredMinusItemList().size());
        assertEquals(2,promotionSet.getSecondHalfItemList().size());

//        assertNotNull(shoppingMall.promotionSet.getBasicItemList());
//        assertNotNull(shoppingMall.promotionSet.getDiscountItemList());
//        assertNotNull(shoppingMall.promotionSet.getHundredMinusItemList());
//        assertNotNull(shoppingMall.promotionSet.getSecondHalfItemList().size());

    }

    @Test
    public void should_get_discount_cost_when_item_have_promotion()  {

        List<DiscountItem> discountItemList= new ArrayList<DiscountItem>();
        DiscountItem discountItem = new DiscountItem();
        discountItem.setProductId("01");
        discountItem.setDiscountPercentage(70);
        discountItemList.add(discountItem);

        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setDiscountItemList(discountItemList);
        Product item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(5);

        ShoppingMall shoppingMall= new ShoppingMall();
        shoppingMall.setPromotionSet(promotionSet);
        item = shoppingMall.decorateDiscount(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(210.0));
    }
    @Test
    public void should_not_get_discount__when_item_not_have_promotion() {

        List<DiscountItem> discountItemList= new ArrayList<DiscountItem>();
        DiscountItem discountItem = new DiscountItem();
        discountItem.setProductId("02");
        discountItem.setDiscountPercentage(70);
        discountItemList.add(discountItem);
        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setDiscountItemList(discountItemList);
        Product item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(5);

        ShoppingMall shoppingMall= new ShoppingMall();
        shoppingMall.setPromotionSet(promotionSet);
        item = shoppingMall.decorateDiscount(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(300.0));
    }

    @Test
    public void should_get_secondHalf_promotion__when_item_have_promotion()  {

        List<SecondHalfItem> secondHalfItemList= new ArrayList<SecondHalfItem>();
        SecondHalfItem secondHalfItem = new SecondHalfItem();
        secondHalfItem.setProductId("01");
        secondHalfItemList.add(secondHalfItem);
        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setSecondHalfItemList(secondHalfItemList);
        Product item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(2);

        ShoppingMall shoppingMall= new ShoppingMall();
        shoppingMall.setPromotionSet(promotionSet);
        item = shoppingMall.decorateSecondHalfItem(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(90.0));
    }

    @Test
    public void should_get_hundredMinus_promotion__when_item_have_promotion()  {

        List<HundredMinusItem> hundredMinusItemList= new ArrayList<HundredMinusItem>();
        HundredMinusItem hundredMinusItem = new HundredMinusItem();
        hundredMinusItem.setProductId("01");
        hundredMinusItem.setReduction(5);
        hundredMinusItemList.add(hundredMinusItem);
        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setHundredMinusItemList(hundredMinusItemList);
        Product item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(2);

        ShoppingMall shoppingMall= new ShoppingMall();
        shoppingMall.setPromotionSet(promotionSet);
        item = shoppingMall.decorateHundredItem(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(115.0));
    }


    @Test
    public void should_get_checkout_when_cart_comes() throws Exception {

        ShoppingMall shoppingMall= new ShoppingMall();

        Cart cart= new Cart();
        PromotionSet promotionSet = new PromotionSet();
        shoppingMall.setPromotionSet(promotionSet);
        shoppingMall.initialShoppingMall();

        List<Item> cartList=cart.putInCart("/home/sun/IdeaProjects/ShoppingProject-master/File/cart.txt", shoppingMall.getPromotionSet().getBasicItemList());
        shoppingMall.checkOut(cartList);

        assertThat(shoppingMall.getDisPayment(), is(394.0));
        assertThat(shoppingMall.getOrigPayment(), is(540.0));

    }


}