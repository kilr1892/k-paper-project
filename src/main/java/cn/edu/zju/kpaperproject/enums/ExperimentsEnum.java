package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 实验次数枚举类
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class ExperimentsEnum {

    public static int experimentsNumber;

    @Value("${experiments.number}")
    public void setExperimentsNumber(int experimentsNumber) {
        ExperimentsEnum.experimentsNumber = experimentsNumber;
    }
}
