package cn.edu.zju.kpaperproject;

import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void test2() {
        int[] i = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int j = 0; j < i.length; j++) {
            System.out.println("i % 5 = " + i[j] % 5);
        }
    }

    @Test
    public void test3() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int[] arr = new int[]{1,2,3,4,5};
        arrayList = aa(arr);
        System.out.println(arrayList.size());

    }

    private ArrayList<Integer> aa(int[] arr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        return arrayList;
    }
}
