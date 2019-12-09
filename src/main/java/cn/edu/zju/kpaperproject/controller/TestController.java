package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.service.StartTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    StartTaskService startTaskService;
    @Autowired
    ProcessTaskService processTaskService;

    @GetMapping("/test")
    public String test() {
//        startTaskService.startTask(1);

        ArrayList<ArrayList<EngineFactoryManufacturingTask>> engineFactoryTaskDecomposition = startTaskService.genEngineFactoryTaskDecomposition(1);
        ArrayList<ArrayList<SupplierTask>> supplierTask = startTaskService.genSupplierTask(1);
        LinkedHashMap<EngineFactoryManufacturingTask, ArrayList<SupplierTask>> engineFactoryManufacturingTaskArrayListLinkedHashMap = processTaskService.roughMatching(engineFactoryTaskDecomposition, supplierTask);

        Set<EngineFactoryManufacturingTask> engineFactoryManufacturingTasks = engineFactoryManufacturingTaskArrayListLinkedHashMap.keySet();

        for (EngineFactoryManufacturingTask engineFactoryManufacturingTask : engineFactoryManufacturingTasks) {
            System.out.println(engineFactoryManufacturingTask);
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
