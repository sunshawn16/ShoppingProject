package com.tw.shopping.shoppingmall;

import com.tw.shopping.product.*;
import com.tw.shopping.promotion.*;
import com.tw.shopping.util.CartParse;
import com.tw.shopping.util.Parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 14-12-4.
 */
public class Cart {

    private Parse cartItem;
    PromotionSet promotionSet;
    public Cart(){}
    public Cart(PromotionSet promotionSet){
        this.promotionSet=promotionSet;
    }

    //添加购物车和数据整理
    public List<Item> putInCart(String path){

        //解析购物车文件
        List<Item> cartList = readCart(path);

        //读取原始价格
        InitialOrigPrice(cartList);

        //整理乱序的购物车,以得到实数量
        return settleCart(cartList);

    }

    public List<Item> t_settleCart(List<Item> cartList) {
        return  settleCart(cartList);

    }

    private List<Item> settleCart(List<Item> cartList) {
        List<Item> finalList=new ArrayList<Item>();
        int num=0;
        int cartListSize=cartList.size();
        for(int i=0;i<cartListSize;i++){

            if(i==0) {
                finalList.add(cartList.get(i));
                continue;
            }
            // System.out.println(finalCart.size());
            int finalListSize=finalList.size();
            int j;
            for(j=0;j< finalListSize;j++){
                if(finalList.get(j).getProductId().equals(cartList.get(i).getProductId())){
                    //System.out.println("+++");
                    num= cartList.get(i).getNum();
                    num=num+finalList.get(j).getNum();
                    finalList.get(j).setNum(num);
                    break;
                }
            }
            if(j== finalList.size()){
                finalList.add(cartList.get(i));
            }

        }
        return finalList;
    }

    private List<Item> readCart(String path) {
        cartItem = new CartParse();
        List<Item> cartList= null;
        try {
            cartList = cartItem.parse(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    public void t_InitialOrigPrice(List<Item> cartList) {
        InitialOrigPrice(cartList);
    }

    private void InitialOrigPrice(List<Item> cartList) {
        for (Item item : cartList) {
            for (int i = 0; i < promotionSet.getBasicItemList().size(); i++) {
                if (item.getProductId().equals(promotionSet.getBasicItemList().get(i).getProductId())) {
                    item.setOrigPrice(promotionSet.getBasicItemList().get(i).getOrigPrice());
                }
            }
        }
    }


}
