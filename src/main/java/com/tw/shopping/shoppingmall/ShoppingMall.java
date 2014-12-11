package com.tw.shopping.shoppingmall;


import com.tw.shopping.promotion.*;
import com.tw.shopping.product.*;
import com.tw.shopping.util.Parse;

import java.io.IOException;
import java.util.List;

/**
 * Created by sun on 14-12-7.
 */
public class ShoppingMall {
    private Parse basic;
    private Parse discount;
    private Parse second;
    private Parse hundred;
    private double origPayment;
    private double disPayment;

    PromotionSet promotionSet;

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

    public PromotionSet getPromotionSet() {
        return promotionSet;
    }

    public void setPromotionSet(PromotionSet promotionSet) {
        this.promotionSet = promotionSet;
    }

    //    List<com.tw.shopping.product.Item> basicItemList;
//    List<com.tw.shopping.promotion.DiscountItem> discountItemList;
//    List<com.tw.shopping.promotion.SecondHalfItem> secondHalfItemList;
//    List<com.tw.shopping.promotion.HundredMinusItem> hundredMinusItemList;


    //读入商场基本数据和促销活动
    public void initialShoppingMall(){
        basic = new BasicItemParse();
        discount = new DiscountParse();
        second = new SecondHalfParse();
        hundred = new HundredMinusParse();

        try {
            promotionSet.setBasicItemList(basic.parse("./File/itemlist.txt")); ;
            promotionSet.setDiscountItemList(discount.parse("./File/discount_promotion")); ;
            promotionSet.setSecondHalfItemList(second.parse("./File/second_half_promotion.txt"));
            promotionSet.setHundredMinusItemList(hundred.parse("./File/hundredMinus")); ;

        }
        catch(IOException e) {
            System.out.println("preparation Failed");
        }
    }

    //结算环节加打印小票
    public void checkOut(List<Item> cartList){

        for(Product item:cartList){
            String productId=item.getProductId();
            item = decorateDiscount(item, productId);
            item = decorateSecondHalfItem(item,productId);
            item = decorateHundredItem(item, productId);
            item.cost();

            origPayment=origPayment+item.getOrigPrice()*item.getNum();
            disPayment = disPayment +item.getPayment();
            System.out.println(item.getProductId()+":       "+item.getNum()+"   "+item.getOrigPrice()+"   "+item.getPayment());
        }


        disPayment=SpecialOffer.specialOfferHundredMinus(3,disPayment);

        System.out.println("Total  (before  after  U save)");
        System.out.println(disPayment +"   "+origPayment+"    "+ disPayment +"    "+(origPayment- disPayment));



    }







    //产品系列活动
    private Product decorateDiscount(Product item, String productId) {
        for(DiscountItem discountItem:promotionSet.getDiscountItemList()){
            if(productId.equals(discountItem.getProductId())){
                item = new Discount(item,discountItem.getDiscountPercentage());
                break;
            }
        }
        return item;
    }

    private Product decorateHundredItem(Product item, String productId) {
        for(HundredMinusItem hundredMinusItem:promotionSet.getHundredMinusItemList()){
            if(productId.equals(hundredMinusItem.getProductId())){
                item=new HundredMinus(item,hundredMinusItem.getReduction());
                break;
            }
        }
        return item;
    }

    private Product decorateSecondHalfItem(Product item,String productId){
        for(SecondHalfItem secondHalfItem:promotionSet.getSecondHalfItemList()) {
            if (productId.equals(secondHalfItem.getProductId())) {
                item = new SecondHalf(item);
                break;
            }
        }
        return item;
    }

    //主函数

    public static void main(String args[]){
        ShoppingMall shoppingMall= new ShoppingMall();
        Cart cart= new Cart();
        //cart.setPromotionSet(shoppingMall.promotionSet);

        PromotionSet promotionSet=new PromotionSet();
        shoppingMall.setPromotionSet(promotionSet);
        shoppingMall.initialShoppingMall();



        shoppingMall.checkOut(cart.putInCart("./File/cart.txt",shoppingMall.promotionSet.getBasicItemList()));


    }





}
