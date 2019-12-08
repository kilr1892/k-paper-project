package cn.edu.zju.kpaperproject.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
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
