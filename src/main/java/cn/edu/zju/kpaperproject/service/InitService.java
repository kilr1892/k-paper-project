package cn.edu.zju.kpaperproject.service;

/**
 * 实验初始化服务
 *
 * @author RichardLee
 * @version v1.0
 */
public interface InitService {

    /**
     * 初始化实验(主机厂/ 供应商/ 关系矩阵 )
     * @param experimentsNumber 实验次数
     */
    void init(int experimentsNumber);
}
