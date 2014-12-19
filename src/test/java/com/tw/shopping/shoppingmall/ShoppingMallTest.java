package com.tw.shopping.shoppingmall;


import com.tw.shopping.product.Item;
import com.tw.shopping.product.ProductImpl;
import com.tw.shopping.promotion.*;
import com.tw.shopping.util.CartParse;
import com.tw.shopping.util.Parse;
import com.tw.shopping.util.Path;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShoppingMallTest {
//    @Test
//    public void should_display_msg() throws Exception {
//
//        assertEquals(1,new ShoppingMall().printTotal());
//    }


    @Test
    public void should_get_discount_cost_when_item_have_promotion()  {

        List<DiscountItem> discountItemList= new ArrayList<DiscountItem>();
        DiscountItem discountItem = new DiscountItem();
        discountItem.setProductId("01");
        discountItem.setDiscountPercentage(70);
        discountItemList.add(discountItem);

        PromotionGroup promotionGroup = new PromotionGroup();
        promotionGroup.setDiscountItemList(discountItemList);
        ProductImpl item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(5);

        ShoppingMallImpl shoppingMall= new ShoppingMallImpl();
        shoppingMall.setPromotionGroup(promotionGroup);
        item =(ProductImpl) shoppingMall.decorateDiscount(item, item.getProductId());
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
        PromotionGroup promotionGroup = new PromotionGroup();
        promotionGroup.setDiscountItemList(discountItemList);
        ProductImpl item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(5);

        ShoppingMallImpl shoppingMall= new ShoppingMallImpl();
        shoppingMall.setPromotionGroup(promotionGroup);
        item =(ProductImpl) shoppingMall.decorateDiscount(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(300.0));
    }

    @Test
    public void should_get_secondHalf_promotion__when_item_have_promotion()  {

        List<SecondHalfItem> secondHalfItemList= new ArrayList<SecondHalfItem>();
        SecondHalfItem secondHalfItem = new SecondHalfItem();
        secondHalfItem.setProductId("01");
        secondHalfItemList.add(secondHalfItem);
        PromotionGroup promotionGroup = new PromotionGroup();
        promotionGroup.setSecondHalfItemList(secondHalfItemList);
        ProductImpl item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(2);

        ShoppingMallImpl shoppingMall= new ShoppingMallImpl();
        shoppingMall.setPromotionGroup(promotionGroup);
        item =(ProductImpl) shoppingMall.decorateSecondHalfItem(item, item.getProductId());
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
        PromotionGroup promotionGroup = new PromotionGroup();
        promotionGroup.setHundredMinusItemList(hundredMinusItemList);
        //ProductImpl item= new Item();
        ProductImpl item =new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(2);

        ShoppingMallImpl shoppingMall= new ShoppingMallImpl();
        shoppingMall.setPromotionGroup(promotionGroup);
        item = (ProductImpl)shoppingMall.decorateHundredItem(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(115.0));
    }


    @Test
    public void should_get_checkout_when_cart_comes() throws Exception {

        String path=Path.getPath();

        Parse basicItemParse = new BasicItemParse();
        Parse discountItemParse= new DiscountParse();
        Parse secondHalfParse= new SecondHalfParse();
        Parse hundredMinusParse= new HundredMinusParse();
        Parse cartParse= new CartParse();



//        Parse basicItemParse = new BasicItemParse();
//        Parse discountItemParse= new DiscountParse();
//        Parse secondHalfParse= new SecondHalfParse();
//        Parse hundredMinusParse= new HundredMinusParse();
//        Parse cartParse= new CartParse();

        List<Item> basicItemList=basicItemParse.parse(path+"/File/itemList");
        List<DiscountItem> discountItemList= discountItemParse.parse(path+"/File/discount");
        List<SecondHalfItem>secondHalfItemList=secondHalfParse.parse(path+"/File/secondHalf");
        List<HundredMinusItem> hundredMinusList=hundredMinusParse.parse(path+"/File/hundredMinus");
        List<Item> cartList=cartParse.parse(path + "/File/cart");

        PromotionGroup promotionGroup =new PromotionGroup();
        promotionGroup.setBasicItemList(basicItemList);
        promotionGroup.setDiscountItemList(discountItemList);
        promotionGroup.setHundredMinusItemList(hundredMinusList);
        promotionGroup.setSecondHalfItemList(secondHalfItemList);


        ShoppingMallImpl shoppingMall= new ShoppingMallImpl();
        shoppingMall.setPromotionGroup(promotionGroup);
        CartImpl cart= new CartImpl();


        List<Item> finalCartList=cart.InitialCart(cartList, promotionGroup.getBasicItemList());

        shoppingMall.checkOut(finalCartList);

        assertThat(shoppingMall.getDisPayment(),is(394.0));


    }


}