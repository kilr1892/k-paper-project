package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public class TaskDecompositionEnum {
    @Value("${experiments.taskDecomposition.ejd}")
    public static double taskDecompositionDjd;
    @Value("${experiments.taskDecomposition.xic}")
    public static double taskDecompositionXic;
    @Value("${experiments.taskDecomposition.ejc}")
    public static double taskDecompositionEjc;
    @Value("${experiments.taskDecomposition.xiq}")
    public static double taskDecompositionXiq;
}
