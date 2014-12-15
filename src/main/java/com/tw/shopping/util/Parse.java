package com.tw.shopping.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 14-12-4.
 */
public abstract class Parse<V> {


    public List<V> parse(String path)throws IOException {

        File file = new File(path);

        BufferedReader reader = null;
        List<V> valueList=null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            valueList = readToList(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("error");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return valueList;
    }

    public List<V> readToList(BufferedReader reader) throws IOException {
        String tempString = null;
        List<V> valueList= new ArrayList<V>();
        while ((tempString = reader.readLine()) != null) {
            valueList.add(convert(tempString));
        }
        return  valueList;
    }


   public abstract V convert(String line);

}
