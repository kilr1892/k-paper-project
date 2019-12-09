package cn.edu.zju.kpaperproject.service;

/**
 * 任务计算相关
 *
 * @author RichardLee
 * @version v1.0
 */
public interface TaskService {

    /**
     * 任务开始的相关内容获取
     * @param cycleTime    循环次数, 从1开始
     */
    void startTask(int cycleTime);
}
