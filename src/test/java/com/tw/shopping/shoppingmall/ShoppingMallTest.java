package com.tw.shopping.shoppingmall;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tw.shopping.Module.*;
import com.tw.shopping.product.Item;
import com.tw.shopping.product.Product;
import com.tw.shopping.product.ProductImpl;
import com.tw.shopping.promotion.*;
import com.tw.shopping.util.Parse;
import com.tw.shopping.util.Path;
import org.junit.Test;
import sun.awt.image.GifImageDecoder;

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

        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setDiscountItemList(discountItemList);
        ProductImpl item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(5);

        ShoppingMallImpl shoppingMall=(ShoppingMallImpl)Guice.createInjector(new ShoppingMallModule()).getInstance(ShoppingMall.class);
        shoppingMall.setPromotionSet(promotionSet);
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
        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setDiscountItemList(discountItemList);
        ProductImpl item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(5);

        ShoppingMallImpl shoppingMall=(ShoppingMallImpl)Guice.createInjector(new ShoppingMallModule()).getInstance(ShoppingMall.class);
        shoppingMall.setPromotionSet(promotionSet);
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
        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setSecondHalfItemList(secondHalfItemList);
        ProductImpl item= new Item();
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(2);

        ShoppingMallImpl shoppingMall=(ShoppingMallImpl)Guice.createInjector(new ShoppingMallModule()).getInstance(ShoppingMall.class);
        shoppingMall.setPromotionSet(promotionSet);
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
        PromotionSet promotionSet= new PromotionSet();
        promotionSet.setHundredMinusItemList(hundredMinusItemList);
        //ProductImpl item= new Item();
        ProductImpl item =(ProductImpl) Guice.createInjector(new ProductModule()).getInstance(Product.class);
        item.setProductId("01");
        item.setOrigPrice(60.0);
        item.setNum(2);

        ShoppingMallImpl shoppingMall=(ShoppingMallImpl)Guice.createInjector(new ShoppingMallModule()).getInstance(ShoppingMall.class);
        shoppingMall.setPromotionSet(promotionSet);
        item = (ProductImpl)shoppingMall.decorateHundredItem(item, item.getProductId());
        item.cost();

        assertThat(item.getPayment(),is(115.0));
    }


    @Test
    public void should_get_checkout_when_cart_comes() throws Exception {

        String path=Path.getPath();

        Injector injector = Guice.createInjector(new BasicItemModule());
        Parse basicItemParse = injector.getInstance(Parse.class);
        Injector injector1=Guice.createInjector(new HundredMinusModule());
        Parse hundredMinusParse= injector1.getInstance(Parse.class);
        Parse discountItemParse=Guice.createInjector(new DiscountModule()).getInstance(Parse.class);
        Parse secondHalfParse= Guice.createInjector(new SecondHalfModule()).getInstance(Parse.class);
        Parse cartParse= Guice.createInjector(new CartParseModule()).getInstance(Parse.class);

//        basicItemParse.parse();
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

        PromotionSet promotionSet=new PromotionSet();
        promotionSet.setBasicItemList(basicItemList);
        promotionSet.setDiscountItemList(discountItemList);
        promotionSet.setHundredMinusItemList(hundredMinusList);
        promotionSet.setSecondHalfItemList(secondHalfItemList);

        ShoppingMall shoppingMall=Guice.createInjector(new ShoppingMallModule()).getInstance(ShoppingMall.class);
//        ShoppingMallImpl shoppingMall= new ShoppingMallImpl();
        shoppingMall.setPromotionSet(promotionSet);
        Cart cart = Guice.createInjector(new CartModule()).getInstance(Cart.class);


        List<Item> finalCartList=cart.InitialCart(cartList,promotionSet.getBasicItemList());

        shoppingMall.checkOut(finalCartList);

        assertThat(shoppingMall.getDisPayment(),is(394.0));


    }


}