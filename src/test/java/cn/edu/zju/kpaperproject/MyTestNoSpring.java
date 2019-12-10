package cn.edu.zju.kpaperproject;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class MyTestNoSpring {
    @Test
    public void test1() {
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
        stringStringLinkedHashMap.put("1", "1");
        stringStringLinkedHashMap.put("2", "1");
        stringStringLinkedHashMap.put("3", "1");

        for (Map.Entry<String, String> stringStringEntry : stringStringLinkedHashMap.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            System.out.println(key + "  :  " + value);
        }
        System.out.println("===================");

        stringStringLinkedHashMap.put("1", "5");
        for (Map.Entry<String, String> stringStringEntry : stringStringLinkedHashMap.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            System.out.println(key + "  :  " + value);

        }
    }
}