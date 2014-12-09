package com.tw.shopping.promotion;

import com.tw.shopping.product.Product;
import com.tw.shopping.promotion.Promotion;

/**
 * Created by sun on 14-12-4.
 */
public class SecondHalf extends Promotion {
    Product product;

    public SecondHalf(Product product){this.product=product;}

    @Override
    public void cost() {
        product.cost();
//        System.out.println(product.num+"   **   "+product.origPrice);

        this.productId=product.getProductId();
        this.num=product.getNum();
        this.origPrice =product.getOrigPrice();
        double numberT=(double)product.getNum()/2;
        this.disPrice=product.getPayment() /product.getNum();
//        System.out.println(numberT+"   **   "+disPrice);
        this.payment =Math.ceil(numberT)*disPrice+Math.floor(numberT)*(disPrice/2);



    }


}
