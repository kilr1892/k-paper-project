package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class ExperimentsEnum {

    @Value("${experiments.number}")
    public static int experimentsNumber;

}
