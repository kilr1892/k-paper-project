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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class MainController {

    @Autowired
    InitService initService;
    @Autowired
    StartTaskService startTaskService;
    @Autowired
    ProcessTaskService processTaskService;
    @Autowired
    BeforeNextTask beforeNextTask;


    @RequestMapping("/start/{experimentsNumber}")
    public String startService(@PathVariable("experimentsNumber") int experimentsNumber) {
        initService.init(experimentsNumber);
        int cycleTime = 1;
        int cycleTimesMax = 10;
        while (cycleTime < cycleTimesMax) {
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
            cycleTime++;
        }
        
        return "success";
    }
}
