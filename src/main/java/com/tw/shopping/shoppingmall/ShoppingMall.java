package com.tw.shopping.shoppingmall;


import com.tw.shopping.promotion.*;
import com.tw.shopping.product.*;
import com.tw.shopping.util.CartParse;
import com.tw.shopping.util.Parse;
import com.tw.shopping.util.Path;

import java.io.IOException;
import java.util.List;

/**
 * Created by sun on 14-12-7.
 */
public class ShoppingMall {

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

    public ShoppingMall(){

    }

    public ShoppingMall(PromotionSet promotionSet){
        this.promotionSet = promotionSet;
    }


    //    List<com.tw.shopping.product.Item> basicItemList;
//    List<com.tw.shopping.promotion.DiscountItem> discountItemList;
//    List<com.tw.shopping.promotion.SecondHalfItem> secondHalfItemList;
//    List<com.tw.shopping.promotion.HundredMinusItem> hundredMinusItemList;
//    public ShoppingMall(List<Item> basicItemList){this.;}怎么办?

    public void printTotal() {
        System.out.println("Total  (before  after  U save)");
        System.out.println(disPayment +"   "+origPayment+"    "+ disPayment +"    "+(origPayment- disPayment));

    }

    public void printItem(Product item) {
        System.out.println(item.getProductId()+":       "+item.getNum()+"   "+item.getOrigPrice()+"   "+item.getPayment());
    }

    //产品系列活动
    public Product decorateDiscount(Product item,String productIdOrig) {//productId 存在没有意义,

        for(DiscountItem discountItem:promotionSet.getDiscountItemList()){
            if(productIdOrig.equals(discountItem.getProductId())){
                item = new Discount(item,discountItem.getDiscountPercentage());
                break;
            }
        }
        return item;
    }

    public Product decorateHundredItem(Product item,String productIdOrig) {
        for(HundredMinusItem hundredMinusItem:promotionSet.getHundredMinusItemList()){
            if(productIdOrig.equals(hundredMinusItem.getProductId())){
                item=new HundredMinus(item,hundredMinusItem.getReduction());
                break;
            }
        }
        return item;
    }

    public Product decorateSecondHalfItem(Product item,String productIdOrig){
        for(SecondHalfItem secondHalfItem:promotionSet.getSecondHalfItemList()) {
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

    //结算环节加打印小票
    public void checkOut(List<Item> cartList){

        for(Product item:cartList){
            String productIdOrig=item.getProductId();

            item = decorateDiscount(item,productIdOrig);
            item = decorateSecondHalfItem(item,productIdOrig);
            item = decorateHundredItem(item,productIdOrig);

            item.cost();

            origPayment=origPayment+item.getOrigPrice()*item.getNum();
            disPayment = disPayment +item.getPayment();
            printItem(item);
        }
        disPayment=SpecialOffer.specialOfferHundredMinus(3,disPayment);

        printTotal();
    }

//    public static void main(String args[])throws IOException{
//
//        String path=Path.getPath();
//
//        Parse basicItemParse = new BasicItemParse();
//        Parse discountItemParse= new DiscountParse();
//        Parse secondHalfParse= new SecondHalfParse();
//        Parse hundredMinusParse= new HundredMinusParse();
//        Parse cartParse= new CartParse();
//
//        List<Item> basicItemList=basicItemParse.parse(path+"/File/itemList");
//        List<DiscountItem> discountItemList= discountItemParse.parse(path+"/File/discount");
//        List<SecondHalfItem>secondHalfItemList=secondHalfParse.parse(path+"/File/secondHalf");
//        List<HundredMinusItem> hundredMinusList=hundredMinusParse.parse(path+"/File/hundredMinus");
//        List<Item> cartList=cartParse.parse(path + "/File/cart");
//
//        PromotionSet promotionSet=new PromotionSet();
//        promotionSet.setBasicItemList(basicItemList);
//        promotionSet.setDiscountItemList(discountItemList);
//        promotionSet.setHundredMinusItemList(hundredMinusList);
//        promotionSet.setSecondHalfItemList(secondHalfItemList);
//
//        ShoppingMall shoppingMall= new ShoppingMall();
//        shoppingMall.setPromotionSet(promotionSet);
//        Cart cart= new Cart();
//
//
//        List<Item> finalCartList=cart.InitialCart(cartList,promotionSet.getBasicItemList());
//
//        shoppingMall.checkOut(finalCartList);
//
//    }

}
