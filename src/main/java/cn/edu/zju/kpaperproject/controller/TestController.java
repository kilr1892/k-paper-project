package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.dto.GraphLink;
import cn.edu.zju.kpaperproject.dto.GraphNode;
import cn.edu.zju.kpaperproject.mapper.TbRelationMatrixMapper;
import cn.edu.zju.kpaperproject.pojo.TbEngineFactory;
import cn.edu.zju.kpaperproject.pojo.TbSupplier;
import cn.edu.zju.kpaperproject.vo.GraphVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestfulApi
 *
 * @author RichardLee
 * @version v1.0
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private TbRelationMatrixMapper tbRelationMatrixMapper;

    @GetMapping("/api/graph/{cycleTimes}")
    public Map<String, ArrayList> getMapper(@PathVariable("cycleTimes") int cycleTimes) {
        Map<String, ArrayList> mapResult = new HashMap<>(2);

        List<GraphVo> list = tbRelationMatrixMapper.selectPositionByCycleTime(99);
        // key : node
        ArrayList<GraphNode> listGraphNodes = new ArrayList<>(list.size());
        // key : link
        ArrayList<GraphLink> listGraphLinks = new ArrayList<>(list.size());

        // # 存已经有的工厂id, 和大小
        Map<String, GraphNode> mapEngineFactory = new HashMap<>(100);
        // # 存已经有的供应商id
        Map<String, GraphNode> mapSupplier = new HashMap<>(100);

        String engineFactoryId;
        double relationScore;
        String supplierId;
        TbEngineFactory tbEngineFactory;
        TbSupplier tbSupplier;
        GraphNode graphNode;
        GraphLink graphLink;
        for (GraphVo aGraphVo : list) {
            tbEngineFactory = aGraphVo.getTbEngineFactory();
            engineFactoryId = aGraphVo.getEngineFactoryId();
            relationScore = aGraphVo.getRelationScore();

            tbSupplier = aGraphVo.getTbSupplier();
            supplierId = aGraphVo.getSupplierId();

            // #构造map
            // _主机厂存入map里
            graphNode = mapEngineFactory.get(engineFactoryId);
            if (graphNode != null) {
                // 已有要累加
                double symbolSize = graphNode.getSymbolSize();
                symbolSize += relationScore;
                graphNode.setSymbolSize(symbolSize);
                mapEngineFactory.put(engineFactoryId, graphNode);
            }else {
                graphNode = new GraphNode();
                graphNode.setId(engineFactoryId);
                graphNode.setName("["+tbEngineFactory.getEngineFactoryLocationGX() +","+ tbEngineFactory.getEngineFactoryLocationGY()+"]");
                graphNode.setX(tbEngineFactory.getEngineFactoryLocationGX());
                graphNode.setY(tbEngineFactory.getEngineFactoryLocationGY());
                graphNode.setSymbolSize(relationScore);
                graphNode.setColor("#4f19c7");
                mapEngineFactory.put(engineFactoryId, graphNode);
            }
            // _供应商存入map里
            graphNode = mapSupplier.get(supplierId);
            if (graphNode == null) {
                graphNode = new GraphNode();
                graphNode.setId(supplierId);
                graphNode.setName("["+tbSupplier.getSupplierLocationGX()+","+tbSupplier.getSupplierLocationGY()+"]");
                graphNode.setX(tbSupplier.getSupplierLocationGX());
                graphNode.setY(tbSupplier.getSupplierLocationGY());
                graphNode.setSymbolSize(8);
                graphNode.setColor("#c71969");
                mapSupplier.put(supplierId, graphNode);
            }

            // # links
            // _关系强度大于0.3的连线
            if (relationScore > 0.5) {
                graphLink = new GraphLink();
                graphLink.setSourceId(engineFactoryId);
                graphLink.setTargetId(supplierId);
                listGraphLinks.add(graphLink);
            }
        }
        // #主机厂与供应商node存入集合
        // _主机厂
        for (Map.Entry<String, GraphNode> entry : mapEngineFactory.entrySet()) {
            listGraphNodes.add(entry.getValue());
        }
        // _供应商
        for (Map.Entry<String, GraphNode> entry : mapSupplier.entrySet()) {
            listGraphNodes.add(entry.getValue());
        }
        // #node和link加入map
        mapResult.put("nodes", listGraphNodes);
        mapResult.put("links", listGraphLinks);
        return mapResult;
    }
}
