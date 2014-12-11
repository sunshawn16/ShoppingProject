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
        List<V> valueList= new ArrayList<V>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                //System.out.println(tempString);
                valueList.add(convert(tempString));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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
    protected abstract V convert(String line);

}
