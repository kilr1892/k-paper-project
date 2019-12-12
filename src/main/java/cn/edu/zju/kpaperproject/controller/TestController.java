package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.dto.*;
import cn.edu.zju.kpaperproject.pojo.TbRelationMatrix;
import cn.edu.zju.kpaperproject.service.BeforeNextTask;
import cn.edu.zju.kpaperproject.service.InitService;
import cn.edu.zju.kpaperproject.service.ProcessTaskService;
import cn.edu.zju.kpaperproject.service.StartTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    BeforeNextTask beforeNextTask;

    @GetMapping("/test")
    public String test() {
        int expNum = 0;
        int cycleTime = 1;
//        initService.init(expNum);
        ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTaskDecomposition = startTaskService.genEngineFactoryTaskDecomposition(expNum, cycleTime);
        ArrayList<ArrayList<SupplierTask>> listListSupplierTask = startTaskService.genSupplierTask(expNum, cycleTime);
        Map<String, Double> mapRelationshipMatrix = startTaskService.getMapRelationshipMatrix(expNum, cycleTime);
        Map<String, TbRelationMatrix> mapRelationshipMatrix2WithTbRelationMatrix = startTaskService.getMapRelationshipMatrix2WithTbRelationMatrix(expNum, cycleTime);
        ArrayList<TransactionContract> transactionContracts = processTaskService.getTransactionContracts(listListEngineFactoryTaskDecomposition, listListSupplierTask, mapRelationshipMatrix);
        List<OrderPlus> listOrderPlus = processTaskService.getTransactionSettlement(expNum, cycleTime, transactionContracts, mapRelationshipMatrix, mapRelationshipMatrix2WithTbRelationMatrix);
        List<EngineFactoryFinalProvision> listEngineFactoryFinalProvision = beforeNextTask.getListEngineFactoryFinalProvision(cycleTime, listOrderPlus);

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
