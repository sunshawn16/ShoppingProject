package com.tw.shopping.promotion;

import java.util.List;

/**
 * Created by sun on 14-12-10.
 */
public class SpecialOffer {

    public static double specialOfferHundredMinus(double reduction,double disPayment) {
        if(disPayment >100){
            int totalHundredCount=(int) disPayment /100;
            return disPayment -totalHundredCount*reduction;
        }
        return disPayment;
    }
}
