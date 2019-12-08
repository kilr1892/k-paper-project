package cn.edu.zju.kpaperproject.service;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
public interface InitService {

    /**
     * 初始化实验数值
     * @param experimentsNumber 实验次数
     */
    void init(String experimentsNumber);

    /**
     * 初始化主机厂
     * @param experimentsNumber 实验次数
     */
    void engineFactoryInit(int experimentsNumber);
    /**
     * 初始化供应商
     * @param experimentsNumber 实验次数
     */
    void supplierInit(int experimentsNumber);

    /**
     * 初始化关系矩阵
     * @param experimentsNumber 实验次数
     */
    void relationMatrixInit(String experimentsNumber);
}
