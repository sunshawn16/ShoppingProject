package com.tw.shopping.promotion;

import com.tw.shopping.product.ProductImpl;

/**
 * Created by sun on 14-12-4.
 */
public class SecondHalf extends Promotion {
    ProductImpl productImpl;

    public SecondHalf(ProductImpl productImpl){this.productImpl = productImpl;}

    @Override
    public void cost() {
        productImpl.cost();
//        System.out.println(product.num+"   **   "+product.origPrice);

        this.productId= productImpl.getProductId();
        this.num= productImpl.getNum();
        this.origPrice = productImpl.getOrigPrice();
        double numberT=(double) productImpl.getNum()/2;
        this.disPrice= productImpl.getPayment() / productImpl.getNum();
//        System.out.println(numberT+"   **   "+disPrice);
        this.payment =Math.ceil(numberT)*disPrice+Math.floor(numberT)*(disPrice/2);



    }


}
