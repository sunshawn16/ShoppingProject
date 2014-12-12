package com.tw.shopping.util;

/**
 * Created by sun on 14-12-12.
 */
public class Path {
    public static String getPath(){
        String path=System.getProperty("user.dir");
        //System.out.println(path);
        path=path.replace("/src/test","");
        System.out.println(path);
        return  path;
    }
}
