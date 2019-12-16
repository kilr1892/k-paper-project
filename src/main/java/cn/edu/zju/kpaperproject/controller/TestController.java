package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.dto.SupplierTask;
import cn.edu.zju.kpaperproject.dto.TransactionContract;
import cn.edu.zju.kpaperproject.pojo.*;
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
        int experimentsNumber = 0;
        int cycleTime = 1;
//        initService.init(experimentsNumber);
        List<TbEngineFactory> listEngineFactory = listEngineFactory = startTaskService.getListEngineFactoryWithAlive(experimentsNumber, cycleTime);

        List<TbEngineFactoryDynamic> listEngineFactoryDynamic = new ArrayList<>();
        List<TbSupplier> listSuppliers =startTaskService.getListTbSuppliersWithAlive(experimentsNumber,cycleTime);
        List<TbSupplierDynamic> listSupplierDynamic = new ArrayList<>();

        ArrayList<ArrayList<EngineFactoryManufacturingTask>> listListEngineFactoryTaskDecomposition = startTaskService.genEngineFactoryTaskDecomposition(
                experimentsNumber, cycleTime, listEngineFactory, listEngineFactoryDynamic);
        ArrayList<ArrayList<SupplierTask>> listListSupplierTask = startTaskService.genSupplierTask(
                experimentsNumber, cycleTime, listSuppliers, listSupplierDynamic);
        Map<String, Double> mapRelationshipMatrix = startTaskService.getMapRelationshipMatrix(experimentsNumber, cycleTime);
        Map<String, TbRelationMatrix> mapRelationshipMatrix2WithTbRelationMatrix = startTaskService.getMapRelationshipMatrix2WithTbRelationMatrix(experimentsNumber, cycleTime);
        ArrayList<TransactionContract> listTransactionContract = processTaskService.getTransactionContracts(listListEngineFactoryTaskDecomposition, listListSupplierTask, mapRelationshipMatrix);
        List<OrderPlus> listOrderPlus = processTaskService.getTransactionSettlement(experimentsNumber, cycleTime, listTransactionContract, mapRelationshipMatrix, mapRelationshipMatrix2WithTbRelationMatrix);
        List<EngineFactoryFinalProvision> listEngineFactoryFinalProvision = beforeNextTask.getListEngineFactoryFinalProvision(experimentsNumber,cycleTime, listOrderPlus);
        beforeNextTask.beforeNextTask(experimentsNumber, cycleTime, listEngineFactoryFinalProvision, listOrderPlus, listTransactionContract, listEngineFactory, listEngineFactoryDynamic, listSuppliers, listSupplierDynamic, mapRelationshipMatrix2WithTbRelationMatrix);


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
