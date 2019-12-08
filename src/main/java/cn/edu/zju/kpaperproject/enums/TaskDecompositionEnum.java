package cn.edu.zju.kpaperproject.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Component
public class TaskDecompositionEnum {
    public static int taskDecompositionEjd;
    public static double taskDecompositionXic;
    public static double taskDecompositionEjc210;
    public static double taskDecompositionEjc220;
    public static double taskDecompositionEjc230;
    public static double taskDecompositionEjc240;
    public static double taskDecompositionEjc250;
    public static double taskDecompositionXiq;

    public static double[] taskDecompositionEjcs = {taskDecompositionEjc210, taskDecompositionEjc220, taskDecompositionEjc230, taskDecompositionEjc240, taskDecompositionEjc250};

    @Value("${experiments.taskDecomposition.ejd}")
    public void setTaskDecompositionDjd(int taskDecompositionEjd) {
        TaskDecompositionEnum.taskDecompositionEjd = taskDecompositionEjd;
    }

    @Value("${experiments.taskDecomposition.xic}")
    public void setTaskDecompositionXic(double taskDecompositionXic) {
        TaskDecompositionEnum.taskDecompositionXic = taskDecompositionXic;
    }

    @Value("${experiments.taskDecomposition.ejc210}")
    public void setTaskDecompositionEjc210(double taskDecompositionEjc210) {
        TaskDecompositionEnum.taskDecompositionEjc210 = taskDecompositionEjc210;
    }

    @Value("${experiments.taskDecomposition.ejc220}")
    public void setTaskDecompositionEjc220(double taskDecompositionEjc220) {
        TaskDecompositionEnum.taskDecompositionEjc220 = taskDecompositionEjc220;
    }

    @Value("${experiments.taskDecomposition.ejc230}")
    public void setTaskDecompositionEjc230(double taskDecompositionEjc230) {
        TaskDecompositionEnum.taskDecompositionEjc230 = taskDecompositionEjc230;
    }

    @Value("${experiments.taskDecomposition.ejc240}")
    public void setTaskDecompositionEjc240(double taskDecompositionEjc240) {
        TaskDecompositionEnum.taskDecompositionEjc240 = taskDecompositionEjc240;
    }

    @Value("${experiments.taskDecomposition.ejc250}")
    public void setTaskDecompositionEjc250(double taskDecompositionEjc250) {
        TaskDecompositionEnum.taskDecompositionEjc250 = taskDecompositionEjc250;
    }

    @Value("${experiments.taskDecomposition.xiq}")
    public void setTaskDecompositionXiq(double taskDecompositionXiq) {
        TaskDecompositionEnum.taskDecompositionXiq = taskDecompositionXiq;
    }
}
