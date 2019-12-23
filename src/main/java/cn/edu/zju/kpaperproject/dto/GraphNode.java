package cn.edu.zju.kpaperproject.dto;

import lombok.Data;

/**
 * 每个元素.
 *
 * @author RichardLee
 * @version v1.0
 */
@Data
public class GraphNode {

    private String id;
    private String name;
    private double x;
    private double y;
    private String color;
    private double symbolSize;
    private int totalAsset;
}
