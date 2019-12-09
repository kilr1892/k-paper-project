package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.service.InitService;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.service.StartTaskService;
import cn.edu.zju.kpaperproject.utils.CalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@RestController
public class TestController {

    @Autowired
    InitService initService;
    @Autowired
    StartTaskService startTaskService;
    @Autowired
    ProcessTaskService processTaskService;

    @GetMapping("/test")
    public String test() {
        int expNum = 0;
        int cycleTime = 1;
//        initService.init(expNum);
        ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTaskDecomposition = startTaskService.genEngineFactoryTaskDecomposition(expNum, cycleTime);
        ArrayList<ArrayList<SupplierTask>> listListSupplierTask = startTaskService.genSupplierTask(expNum, cycleTime);
        Map<String, Double> mapRelationshipMatrix = startTaskService.genMapRelationshipMatrix(expNum, cycleTime);

        LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> mapMatchServices = processTaskService.roughMatching(listListEngineFactoryTaskDecomposition, listListSupplierTask);

        Set<EngineFactoryManufacturingTask> engineFactoryManufacturingTasks = mapMatchServices.keySet();
        for (EngineFactoryManufacturingTask engineFactoryManufacturingTask : engineFactoryManufacturingTasks) {

            System.out.println("==============start============");
            String engineFactoryId = engineFactoryManufacturingTask.getEngineFactoryId();
            ArrayList<SupplierTask> supplierTasks = mapMatchServices.get(engineFactoryManufacturingTask);
            if (supplierTasks.size() == 0) {
                continue;
            }
            SupplierTask supplierTask = supplierTasks.get(0);
            String supplierId = supplierTask.getSupplierId();
            Double relationScore = mapRelationshipMatrix.get(engineFactoryId + supplierId);
            System.out.print("key = " + engineFactoryId + supplierId +"||||||");
            System.out.println("value : relationScore = " + relationScore);
            double relationshipStrength = CalculationUtils.calRelationshipStrength(engineFactoryManufacturingTask, supplierTask, mapRelationshipMatrix);
            System.out.println("relationshipStrength = " + relationshipStrength);
            double relationshipStrength1 = CalculationUtils.calRelationshipStrength(engineFactoryManufacturingTask, supplierTask, mapRelationshipMatrix);
            System.out.println();
            System.out.println("=============================");
            System.out.println("================end==========");
        }

        System.out.println();

        return "success";
    }

    @GetMapping("/test2")
    public String test2() {
        return "success";
    }
    @GetMapping("/test3")
    public String test3() {
        return "success";
    }
}
