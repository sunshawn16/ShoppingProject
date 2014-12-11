package com.tw.shopping.promotion;

import com.tw.shopping.product.Item;

import java.util.List;

/**
 * Created by sun on 14-12-7.
 */
public class PromotionSet {
    List<Item> basicItemList;
    List<DiscountItem> discountItemList;
    List<SecondHalfItem> secondHalfItemList;
    List<HundredMinusItem> hundredMinusItemList;

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
