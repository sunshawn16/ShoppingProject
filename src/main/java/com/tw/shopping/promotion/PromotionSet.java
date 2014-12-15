package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;
import com.tw.shopping.util.Parse;

import java.util.List;

/**
 * Created by sun on 14-12-7.
 */
public class PromotionSet {
    private Parse discount;
    private Parse second;
    private Parse hundred ;
    private Parse basic;
    List<Item> basicItemList;
    List<HundredMinusItem> hundredMinusItemList;
    List<SecondHalfItem> secondHalfItemList;
    List<DiscountItem> discountItemList;

    public Parse getDiscount() {
        return discount;
    }

    public void setDiscount(Parse discount) {
        this.discount = discount;
    }

    public Parse getSecond() {
        return second;
    }

    public void setSecond(Parse second) {
        this.second = second;
    }

    public Parse getHundred() {
        return hundred;
    }

    public void setHundred(Parse hundred) {
        this.hundred = hundred;
    }

    public Parse getBasic() {
        return basic;
    }

    public void setBasic(Parse basic) {
        this.basic = basic;
    }


    public List<HundredMinusItem> getHundredMinusItemList() {
        return hundredMinusItemList;
    }

    public void setHundredMinusItemList(List<HundredMinusItem> hundredMinusItemList) {
        this.hundredMinusItemList = hundredMinusItemList;
    }

    public List<Item> getBasicItemList() {
        return basicItemList;
    }

    public void setBasicItemList(List<Item> basicItemList) {
        this.basicItemList = basicItemList;
    }

    public List<DiscountItem> getDiscountItemList() {
        return discountItemList;
    }

    public void setDiscountItemList(List<DiscountItem> discountItemList) {
        this.discountItemList = discountItemList;
    }

    public List<SecondHalfItem> getSecondHalfItemList() {
        return secondHalfItemList;
    }

    public void setSecondHalfItemList(List<SecondHalfItem> secondHalfItemList) {
        this.secondHalfItemList = secondHalfItemList;
    }






}
