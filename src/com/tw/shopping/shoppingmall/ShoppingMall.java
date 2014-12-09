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

//    List<com.tw.shopping.product.Item> basicItemList;
//    List<com.tw.shopping.promotion.DiscountItem> discountItemList;
//    List<com.tw.shopping.promotion.SecondHalfItem> secondHalfItemList;
//    List<com.tw.shopping.promotion.HundredMinusItem> hundredMinusItemList;
    PromotionSet promotionSet=new PromotionSet();

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
        specialOfferHundredMinus(3);

        System.out.println("Total  (before  after  U save)");
        System.out.println(disPayment +"   "+origPayment+"    "+ disPayment +"    "+(origPayment- disPayment));



    }


   // 购物车附加活动-购物车满百减----考虑单独提出来一个类
    private void specialOfferHundredMinus(double reduction) {
        if(disPayment >100){
            int totalHundredCount=(int) disPayment /100;
            disPayment = disPayment -totalHundredCount*reduction;
        }
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
        shoppingMall.initialShoppingMall();
        Cart cart= new Cart(shoppingMall.promotionSet);

        shoppingMall.checkOut(cart.putInCart("./File/cart.txt"));


    }





}