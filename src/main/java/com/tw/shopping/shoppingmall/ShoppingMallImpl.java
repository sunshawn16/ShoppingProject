package com.tw.shopping.shoppingmall;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.tw.shopping.Module.*;
import com.tw.shopping.promotion.*;
import com.tw.shopping.product.*;
import com.tw.shopping.util.Parse;
import com.tw.shopping.util.Path;

import java.io.IOException;
import java.util.List;

/**
 * Created by sun on 14-12-7.
 */
public class ShoppingMallImpl implements ShoppingMall{

    private double origPayment;
    private double disPayment;

    PromotionGroup promotionGroup;

    public double getOrigPayment() {
        return origPayment;
    }

    public void setOrigPayment(double origPayment) {
        this.origPayment = origPayment;
    }

    public double getDisPayment() {
        return disPayment;
    }

    public void setDisPayment(double disPayment) {
        this.disPayment = disPayment;
    }

    public PromotionGroup getPromotionGroup() {
        return promotionGroup;
    }

    @Inject
    public void setPromotionGroup(PromotionGroup promotionGroup) {
        this.promotionGroup = promotionGroup;
    }

    public ShoppingMallImpl(){

    }

    public ShoppingMallImpl(PromotionGroup promotionGroup){
        this.promotionGroup = promotionGroup;
    }


    //    List<com.tw.shopping.product.Item> basicItemList;
//    List<com.tw.shopping.promotion.DiscountItem> discountItemList;
//    List<com.tw.shopping.promotion.SecondHalfItem> secondHalfItemList;
//    List<com.tw.shopping.promotion.HundredMinusItem> hundredMinusItemList;
//    public ShoppingMall(List<Item> basicItemList){this.;}怎么办?
    @Override
    public void printTotal() {
        System.out.println("Total  (before  after  U save)");
        System.out.println(disPayment +"   "+origPayment+"    "+ disPayment +"    "+(origPayment- disPayment));

    }
    @Override
    public void printItem(ProductImpl item) {
        System.out.println(item.getProductId()+":       "+item.getNum()+"   "+item.getOrigPrice()+"   "+item.getPayment());
    }

    //产品系列活动
    public Product  decorateDiscount(ProductImpl item,String productIdOrig) {//productId 存在没有意义,

        for(DiscountItem discountItem: promotionGroup.getDiscountItemList()){
            if(productIdOrig.equals(discountItem.getProductId())){
                item = new Discount(item,discountItem.getDiscountPercentage());
                break;
            }
        }
        return item;
    }

    public Product decorateHundredItem(ProductImpl item,String productIdOrig) {
        for(HundredMinusItem hundredMinusItem: promotionGroup.getHundredMinusItemList()){
            if(productIdOrig.equals(hundredMinusItem.getProductId())){
                item=new HundredMinus(item,hundredMinusItem.getReduction());
                break;
            }
        }
        return item;
    }

    public Product decorateSecondHalfItem(ProductImpl item,String productIdOrig){
        for(SecondHalfItem secondHalfItem: promotionGroup.getSecondHalfItemList()) {
            if (productIdOrig.equals(secondHalfItem.getProductId())) {
                item = new SecondHalf(item);
                break;
            }
        }
        return item;
    }



    //读入商场基本数据和促销活动
//    public void initialShoppingMall(List<Item> basicItemList,List<DiscountItem> discountItemList,List<SecondHalfItem>secondHalfItemList,List<HundredMinusItem> hundredMinusList) throws IOException {
//
//        promotionSet.setBasicItemList(basicItemList); ;
//        promotionSet.setDiscountItemList(discountItemList); ;
//        promotionSet.setSecondHalfItemList(secondHalfItemList);
//        promotionSet.setHundredMinusItemList(hundredMinusList); ;
//
//    }

    @Override
    public void checkOut(List<Item> cartList){

        for(ProductImpl item:cartList){
            String productIdOrig=item.getProductId();

            item = (ProductImpl)decorateDiscount(item,productIdOrig);
            item = (ProductImpl)decorateSecondHalfItem(item,productIdOrig);
            item = (ProductImpl)decorateHundredItem(item,productIdOrig);

            item.cost();

            origPayment=origPayment+item.getOrigPrice()*item.getNum();
            disPayment = disPayment +item.getPayment();
            printItem(item);
        }
        disPayment=SpecialOffer.specialOfferHundredMinus(3,disPayment);

        printTotal();
    }

    public static void main(String args[])throws IOException {

        String path= Path.getPath();

        Injector injector = Guice.createInjector(new BasicItemModule());
        Parse basicItemParse = injector.getInstance(Parse.class);
        Injector injector1=Guice.createInjector(new HundredMinusModule());
        Parse hundredMinusParse= injector1.getInstance(Parse.class);
        Parse discountItemParse=Guice.createInjector(new DiscountModule()).getInstance(Parse.class);
        Parse secondHalfParse= Guice.createInjector(new SecondHalfModule()).getInstance(Parse.class);
        Parse cartParse= Guice.createInjector(new CartParseModule()).getInstance(Parse.class);



        List<Item> basicItemList=basicItemParse.parse(path+"/File/itemList");
        List<DiscountItem> discountItemList= discountItemParse.parse(path+"/File/discount");
        List<SecondHalfItem>secondHalfItemList=secondHalfParse.parse(path+"/File/secondHalf");
        List<HundredMinusItem> hundredMinusList=hundredMinusParse.parse(path+"/File/hundredMinus");
        List<Item> cartList=cartParse.parse(path + "/File/cart");

        PromotionGroup promotionGroup=new PromotionGroup();
        promotionGroup.setBasicItemList(basicItemList);
        promotionGroup.setDiscountItemList(discountItemList);
        promotionGroup.setHundredMinusItemList(hundredMinusList);
        promotionGroup.setSecondHalfItemList(secondHalfItemList);

        ShoppingMall shoppingMall=Guice.createInjector(new ShoppingMallModule()).getInstance(ShoppingMall.class);
        shoppingMall.setPromotionGroup(promotionGroup);

        Cart cart = Guice.createInjector(new CartModule()).getInstance(Cart.class);


        List<Item> finalCartList=cart.InitialCart(cartList,promotionGroup.getBasicItemList());

        shoppingMall.checkOut(finalCartList);

    }

}
