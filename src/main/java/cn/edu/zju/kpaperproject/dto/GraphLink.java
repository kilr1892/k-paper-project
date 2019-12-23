package cn.edu.zju.kpaperproject.dto;

import lombok.Data;

/**
 * 连接线.
 *
 * @author RichardLee
 * @version v1.0
 */
@Data
public class GraphLink {
    private String sourceId;
    private String targetId;
    private double lineWidth;
}
