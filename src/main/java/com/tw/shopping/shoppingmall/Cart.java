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




    public Cart(){}


    //添加购物车和数据整理
    public List<Item> InitialCart(List<Item> cartList, List<Item> basicList){//输入和输出 做了 多余的事情 要分开;命名;;直接 传进来cartlist ,,,不关心path不要path

        initialOrigPrice(cartList,basicList);

        //整理乱序的购物车,以得到实数量
        return settleCart(cartList);

    }


//    private List<Item> initialCart(List<Item> cartList) throws IOException {
//        //cartItem = new CartParse();不要NEW parse应该是只关心list
//        List<Item> cartList= null;
//        cartList = cartItem.parse(path);
////        try {
////            cartList = cartItem.parse(path);
////        } catch (IOException e) {
////            System.out.println("path has problem!");
////            e.printStackTrace();
////        }
//        return cartList;
//    }

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



    public void t_InitialOrigPrice(List<Item> cartList,List<Item> basicList) {
        initialOrigPrice(cartList,basicList);
    }

    private void initialOrigPrice(List<Item> cartList,List<Item> basicList) {
        for (Item item : cartList) {
            for (int i = 0; i < basicList.size(); i++) {
                if (item.getProductId().equals(basicList.get(i).getProductId())) {
                    item.setOrigPrice(basicList.get(i).getOrigPrice());
                }
            }
        }
    }


}
