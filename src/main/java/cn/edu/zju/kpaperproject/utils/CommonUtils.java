package cn.edu.zju.kpaperproject.utils;

import java.util.UUID;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class CommonUtils {
    /**
     * 生成无"-"的uuid
     *
     * @return  uuid值
     */
    public static String genId() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
